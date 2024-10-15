import { Link } from "react-router-dom";
import { useState } from "react";
import CardHomeCSS from "./CardHome.module.css";

const CardHome = ({post}) => {
  const [count, setCount] = useState(1);

  const increase = () => {
    setCount(prevCount => prevCount + 1);
  };

  const decrease = () => {
    if (count > 1){
        setCount(prevCount => prevCount - 1);
    }
  };

  return (
    <div className={CardHomeCSS.card}>
        <div className={CardHomeCSS.imgCont}>
            <Link className={CardHomeCSS.link} to={`/product/${post.id}`}
            state={{ post: post}}>
                <img src={post.img} alt="" className={CardHomeCSS.img} />
            </Link>
        </div>
        <div className={CardHomeCSS.titleCont}>
            <Link className={CardHomeCSS.link} to={`/product/${post.id}`}
            state={{ post: post}}>
                <h2 className={CardHomeCSS.title}>{post.title}</h2>
            </Link>
        </div>
        <div className={CardHomeCSS.infoCont}>
            <Link className={CardHomeCSS.link} to={`/product/${post.id}`}
            state={{ post: post}}>
                <p className={CardHomeCSS.author}>Unknown</p>
                <p className={CardHomeCSS.rating}>10 out of 10</p>
            </Link>
        </div>
        <div className={CardHomeCSS.priceCont}>
            <Link className={CardHomeCSS.link} to={`/product/${post.id}`}
            state={{ post: post}}>
                <p className={CardHomeCSS.type}>Manga</p>
                <p className={CardHomeCSS.price}>$10.00</p>
            </Link>
        </div>
        <div className={CardHomeCSS.quantityCont}>
            <div className={CardHomeCSS.quantityBorder}>
                <input type="number" className={CardHomeCSS.input} value={count} min={1} readOnly/>
                <div className={CardHomeCSS.quantity}>
                    <button className={CardHomeCSS.increase} onClick={increase}>+</button>
                    <button className={CardHomeCSS.decrease} onClick={decrease}>-</button>
                </div>
            </div>
        </div>
        <div className={CardHomeCSS.btnCont}>
            <button className={CardHomeCSS.btn}>
                Add to Cart
            </button>
        </div>
    </div>
  );
}

export default CardHome;