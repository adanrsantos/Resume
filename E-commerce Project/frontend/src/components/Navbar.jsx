import NavbarCSS from "./Navbar.module.css";

import { Link } from "react-router-dom";

import tempLogo from "../assets/tempLogo.png";
import searchIcon from "../assets/search.png";

const Navbar = ( {handleLogout} ) => {
  return (
    <div className={NavbarCSS.wrapper}>
        <div className={NavbarCSS.logoCont}>
          <Link to="/">
            <img src={tempLogo} alt="" className={NavbarCSS.logo}/>
          </Link>
        </div>
        <div className={NavbarCSS.searchBar}>
          <div className={NavbarCSS.searchBarCont}>
            <input className={NavbarCSS.input} type="text" placeholder="Search..."/>
            <img src={searchIcon} alt="" className={NavbarCSS.icon}/>
          </div>
        </div>
        <ul className={NavbarCSS.list}>
            <li className={NavbarCSS.listItem}><Link className={NavbarCSS.link} to="/contact">Contact</Link></li>
            <li className={NavbarCSS.listItem}><Link className={NavbarCSS.link} to="/orders">Orders</Link></li>
            <li className={NavbarCSS.listItem}><Link className={NavbarCSS.link} onClick={handleLogout}>Logout</Link></li>
            <li className={NavbarCSS.listItem}><Link className={NavbarCSS.link} to="/cart">Cart</Link></li>
        </ul>
    </div>
  );
}

export default Navbar;