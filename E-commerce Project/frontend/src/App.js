import './App.css';
import { useState, useEffect} from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
//import axios from 'axios';

import Home from './pages/Home';
import Login from "./pages/Login";
import Register from "./pages/Register";
import Cart from "./pages/Cart";
import Contact from "./pages/Contact";
import Product from "./pages/Product";
import Order from "./pages/Order";
import Navbar from "./components/Navbar";

const App = () => {

  const [user, setUser] = useState(false);

  useEffect(() => {
    const loggedInUser = localStorage.getItem("user");
    if (loggedInUser){
      setUser(true);
    }
  }, []);

  const handleLogout = () => {
    setUser(false);
    localStorage.removeItem("user");
    console.log("working");
  }

  return (
    <div className="App">
      <BrowserRouter>
        {user && <Navbar handleLogout={handleLogout} />}
        <Routes>
          <Route path="/" element={user ? <Home /> : <Navigate to="/login"/>}/>
          <Route path="/login" element={user ? <Navigate to="/"/> : <Login setUser={setUser}/>}/>
          <Route path="/register" element={user ? <Navigate to="/"/> : <Register />}/>
          <Route path="/cart" element={user ? <Cart /> : <Navigate to="/login"/>}/>
          <Route path="/contact" element={user ? <Contact /> : <Navigate to="/login"/>}/>
          <Route path="/orders" element={user ? <Order /> : <Navigate to="/login"/>}/>
          <Route path="/product/:id" element={user ? <Product /> : <Navigate to="/login"/>}/>

          <Route path="*" element={user ? <Navigate to ="/"/> : <Navigate to="/login"/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;