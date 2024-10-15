import LFormCSS from "./LForm.module.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

const LForm = ( {setUser} ) => {
    
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        if (username === "user" && password === "password"){
            localStorage.setItem("user", username);
            setUser(true);
            navigate("/");
        }
        else{
            alert("Invalid login credentials");
        }
    }
    
    return(
        <div className={LFormCSS.wrapper}>
            <form onSubmit={handleSubmit}>
                <h1 className={LFormCSS.h1}>Login</h1>
                <div className={LFormCSS.inputCont}>
                    <input 
                        className={LFormCSS.input} 
                        type="text" 
                        placeholder="Username" 
                        required
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div> 
                <div className={LFormCSS.inputCont}>
                    <input 
                        className={LFormCSS.input} 
                        type="password" 
                        placeholder="Password" 
                        required
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                
                <button className={LFormCSS.btn} type="submit">Login</button>
                <div className={LFormCSS.registerCont}>
                    <p className={LFormCSS.question}>Don't have an account? 
                        <Link className={LFormCSS.link} to="/register"> Register</Link>
                    </p>
                </div>
            </form>
        </div>
    );
}

export default LForm;