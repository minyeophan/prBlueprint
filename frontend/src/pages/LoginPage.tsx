import { useState } from "react";
import { login } from "../api/auth";

function LoginPage(){
    const [email, setEmail] =useState('');
    const [password, setPassword] =useState('');

    const handleLogin = async() =>{
        const respone = await login(email,password);
        console.log(respone);
    }

    return(
        <div>
            <h2>로그인</h2>
            <input type ="text" value ={email} onChange={(e)=>setEmail(e.target.value)} placeholder ="이메일"/>
            <input type ="password" value ={password} onChange={(e)=>setPassword(e.target.value)} placeholder ="비밀번호"/>

            <button onClick={handleLogin}>로그인</button>
        </div>
    )
}
export default LoginPage;