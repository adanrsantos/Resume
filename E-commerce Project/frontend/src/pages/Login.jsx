import LForm from "../components/LoginComp/LForm";

const Login = ( {setUser} ) => {
    return(
        <div className="loginWrapper">
            <LForm setUser={setUser}/>
        </div>
    );
}

export default Login;