import FilterCSS from "./Filter.module.css";

const Filter = () => {
    return (
        <div className={FilterCSS.wrapper}>
            <div className={FilterCSS.HeaderCont}>
                <h4 className={FilterCSS.Header}>Filter</h4>
            </div>
            <div className={FilterCSS.RatingCont}>
                <h4 className={FilterCSS.SubHeader}>Rating:</h4>
                <div className={FilterCSS.Rating}>
                    <label htmlFor="highest">Highest to Lowest Rating</label>
                    <input type="radio" name="filter" value="highest" id="highest" />
                </div>
                <div className={FilterCSS.Rating}>
                    <label htmlFor="lowest">Lowest to Highest Rating</label>
                    <input type="radio" name="filter" value="lowest" id="lowest" />
                </div>
            </div>
            <div className={FilterCSS.DateCont}>
                <h4 className={FilterCSS.SubHeader}>Date:</h4>
                <div className={FilterCSS.Date}>
                    <label htmlFor="oldest">Oldest - Newest</label>
                    <input type="radio" name="filter" value="oldest" id="oldest"/>
                </div>
                <div className={FilterCSS.Date}>
                    <label htmlFor="newest">Newest - Oldest</label>
                    <input type="radio" name="filter" value="newest" id="newest"/>
                </div>
            </div>
            <div className={FilterCSS.AlpCont}>
                <h4 className={FilterCSS.SubHeader}>Alphabetical Order:</h4>
                <div className={FilterCSS.Alp}>
                    <label htmlFor="atoz">A - Z</label>
                    <input type="radio" name="filter" value="atoz" id="atoz" />
                </div>
                <div className={FilterCSS.Alp}>
                    <label htmlFor="ztoa">Z - A</label>
                    <input type="radio" name="filter" value="ztoa" id="ztoa" />
                </div>
            </div>
        </div>
    );
}

export default Filter;