public class CartDTO {
    public Integer id;

    public CartProductDTO[] products;

    public class CartProductDTO {
        public Integer id;
        public String title;
        public Float price;
        public Integer quantity;
        public Integer total;
        public Float discountPercentage;
        public Float discountedPrice;
    };

    public Float total;

    public Float discountedTotal;

    public Integer userId;

    public Integer totalProducts;

    public Float totalQuantity;

}
