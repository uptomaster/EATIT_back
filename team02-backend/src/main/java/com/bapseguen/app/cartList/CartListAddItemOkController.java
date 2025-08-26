// CartListAddItemOkController.java
@Override
public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Result result = new Result();

    Integer memberNumber = (Integer) req.getSession().getAttribute("memberNumber");
    if (memberNumber == null) {
        result.setRedirect(true);
        result.setPath(req.getContextPath() + "/login/login.lo");
        return result;
    }

    int itemNumber = Integer.parseInt(req.getParameter("itemNumber"));
    int requestedQty;
    try {
        requestedQty = Integer.parseInt(req.getParameter("quantity"));
    } catch (Exception e) {
        requestedQty = 1; // 파라미터가 없거나 이상하면 1
    }
    requestedQty = Math.max(1, requestedQty);

    // 재고 조회 (스냅샷)
    ItemDAO itemDAO = new ItemDAO();
    ItemSnapshotDTO snap = itemDAO.selectSnapshot(itemNumber);
    if (snap == null || !"ON".equalsIgnoreCase(snap.getItemSellState())) {
        // 판매중 아님
        req.getSession().setAttribute("toast", "판매 중이 아닌 상품입니다.");
        result.setRedirect(true);
        result.setPath(req.getHeader("Referer"));
        return result;
    }

    int stock = snap.getItemQuantity();
    if (stock <= 0) {
        req.getSession().setAttribute("toast", "재고가 부족합니다.");
        result.setRedirect(true);
        result.setPath(req.getHeader("Referer"));
        return result;
    }

    // 장바구니 열기/확보 (회원/가게 단일 장바구니 정책)
    CartListDAO cartDAO = new CartListDAO();
    int cartNumber = cartDAO.openOrCreate(memberNumber, snap.getBusinessNumber());

    // 이미 담긴 수량과 합산했을 때 재고 초과 방지
    Integer existingQty = cartDAO.selectQuantity(cartNumber, itemNumber); // 없으면 null
    int targetQty = requestedQty + (existingQty == null ? 0 : existingQty);
    if (targetQty > stock) {
        targetQty = stock;
    }

    if (existingQty == null) {
        cartDAO.insertItem(cartNumber, itemNumber, targetQty, snap.getItemPrice());
    } else {
        int delta = targetQty - existingQty;
        if (delta > 0) {
            cartDAO.increaseQuantity(cartNumber, itemNumber, delta);
        }
    }

    result.setRedirect(true);
    result.setPath(req.getContextPath() + "/cartList/shoppingList.cl");
    return result;
}
