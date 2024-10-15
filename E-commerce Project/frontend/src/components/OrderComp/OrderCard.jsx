import OrderCardCSS from "./OrderCard.module.css";

import { Link } from "react-router-dom";

const OrderCard = ({post}) => {
    return(
        <div className={OrderCardCSS.wrapper}>
            <div className={OrderCardCSS.cont}>
                <div className={OrderCardCSS.imgCont}>
                    <Link className={OrderCardCSS.link} to={`/product/${post.id}`}
                        state={{ post: post}}>
                    <img src={post.img} alt="" className={OrderCardCSS.img} />
                    </Link>
                </div>
                    <div className={OrderCardCSS.titleCont}>
                    <h2 className={OrderCardCSS.title}>{post.title}</h2>
                </div>
                <div className={OrderCardCSS.infoCont}>
                    <p className={OrderCardCSS.author}>Unknown</p>
                    <p className={OrderCardCSS.rating}>10 out of 10</p>
                </div>
                <div className={OrderCardCSS.priceCont}>
                    <p className={OrderCardCSS.type}>Manga</p>
                    <p className={OrderCardCSS.price}>$10.00</p>
                </div>
            </div>
        </div>
    );
}

export default OrderCard;