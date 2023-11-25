import Home from "./layout/home/Home";
import Profile from "./layout/profile/Profile";
import Datasensor from "./layout/data_sensor/DataSensor";
import Action from "./layout/action_history/Action";
import { Route, Routes, Navigate } from "react-router-dom";
import { useState } from "react";
import Login from "./layout/login/Login";

function App() {
  const [isLogin, setIsLogin] = useState(false)
  return (
    <Routes>
      {!isLogin ? <>
        <Route path='/login' element={<Login />} />
        <Route path='*' element={<Navigate to={'/login'} replace />}/>
      </> : <>
        <Route exact index element={<Home/>} ></Route>
        <Route path = "profile" element = {<Profile/>}></Route>
        <Route path = "action-history" element = {<Action/>}></Route>
        <Route path="data-sensor" element = {<Datasensor/>}></Route>
      </>}
    </Routes>
  );
}

export default App;
