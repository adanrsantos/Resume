import { useLocation } from "react-router-dom";
import '../components/ProductComp/product.css'

const Product = () => {
    const location = useLocation();
    const { post } = location.state || {};  // Retrieve the passed post object

    if (!post) {
        return <div>No product data found</div>;
    }

    return (
        <>
            <div className="productWrapper">
                <div className="product">
                    <div className="product-image">
                        <img src={post.img || "placeholder.jpg"} alt={post.title || "No Image"} />
                    </div>
                    <div className="product-details">
                        <h1>{post.title || "Product Title"}</h1>
                        <h2>by {post.author || "Author Name"}</h2>
                        <div className="product-price-rating">
                            <span className="price">$ {post.price || "Price"}</span>
                            <span className="rating">{post.rating || "Rating"}  ★</span>
                        </div>
                        <div className="buy-section">
                            <input type="number" min="1" defaultValue="1" className="quantity-input"/>
                            <button className="buy-button">BUY</button>
                        </div>
                        <div className="product-description">
                            <h3>Overview</h3>
                            <p>{post.description || "Product description"}</p>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Product;