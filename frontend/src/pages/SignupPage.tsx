import { useState } from "react";
import { signup } from "../api/auth";

function SignupPage(){
    const [email, setEmail] =useState('');
    const [password, setPassword] =useState('');
    const [name , setName] =useState('');

    const handleSignUp = async() =>{
        const respone = await signup(email,password,name);
        console.log(respone);
    }

    return(
        <div>
            <h2>회원가입</h2>
            <input type ="text" value ={email} onChange={(e)=>setEmail(e.target.value)} placeholder ="이메일"/>
            <input type ="password" value ={password} onChange={(e)=>setPassword(e.target.value)} placeholder ="비밀번호"/>
            <input type ="text" value ={name} onChange={(e)=>setName(e.target.value)} placeholder ="이름"/>

            <button onClick={handleSignUp}>회원가입</button>
        </div>
    )
}
export default SignupPage;

