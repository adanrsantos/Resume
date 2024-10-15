import OrderCont from "../components/OrderComp/OrderCont";

import { posts } from "../TempData";

const Order = () => {
    return(
        <div className="OrderWrapper">
            <div className="Order">
                <div>
                    <h1 className="OrderHeader">Order History</h1>
                </div>
                <div>
                    <div className="OrderHistory">
                        <OrderCont post={posts}/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Order;