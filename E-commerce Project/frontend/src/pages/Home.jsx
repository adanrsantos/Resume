import CardHome from "../components/HomeComp/CardHome";
import Filter from "../components/HomeComp/Filter";

import {posts} from "../TempData";

const Home = () => {
    return(
        <div className="homeWrapper">
            <div className="Home">
                <div className="filter">
                    <Filter />
                </div>
                <div className="itemHome">
                    {posts.map(post=>(
                        <CardHome post={post}/>
                    ))}
                </div>
            </div>   
        </div>
    );
}

export default Home;