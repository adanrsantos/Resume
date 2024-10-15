import OrderContCSS from "./OrderCont.module.css";

import { Link } from "react-router-dom";

const OrderCont = ({post}) => {
    return(
        <div className={OrderContCSS.wrapper}>
            <div className={OrderContCSS.cont}>
                <div className={OrderContCSS.imgCont}>
                    <Link className={OrderContCSS.link} to={`/product/${post.id}`}
                        state={{ post: post}}>
                    <img src={post.img} alt="" className={OrderContCSS.img} />
                    </Link>
                </div>
                    <div className={OrderContCSS.titleCont}>
                    <h2 className={OrderContCSS.title}>{post.title}</h2>
                </div>
                <div className={OrderContCSS.infoCont}>
                    <p className={OrderContCSS.author}>Unknown</p>
                    <p className={OrderContCSS.rating}>10 out of 10</p>
                </div>
                <div className={OrderContCSS.priceCont}>
                    <p className={OrderContCSS.type}>Manga</p>
                    <p className={OrderContCSS.price}>$10.00</p>
                </div>
            </div>
        </div>
    );
}

export default OrderCont;