import RFormCSS from "./RForm.module.css";

import { Link } from "react-router-dom";

const RForm = () => {
    return(
        <div className={RFormCSS.wrapper}>
            <form action="">
                <h1 className={RFormCSS.h1}>Register</h1>
                <div className={RFormCSS.inputCont}>
                    <input className={RFormCSS.input} type="text" placeholder="Username" required/>
                </div> 
                <div className={RFormCSS.inputCont}>
                    <input className={RFormCSS.input} type="password" placeholder="Password" required/>
                </div>
                <button className={RFormCSS.btn} type="submit">Create Account</button>
                <div className={RFormCSS.registerCont}>
                    <p className={RFormCSS.question}>Go back to Login? 
                    <Link className={RFormCSS.link} to="/login"> Login</Link></p>
                </div>
            </form>
        </div>
    );
}

export default RForm