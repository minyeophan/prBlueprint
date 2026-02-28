import { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { getMe } from "../api/auth";

function PrivateRoute({children}: { children: React.ReactNode}){
    const [status, setStatus] = useState<'loading'|'ok'|'fail'>('loading');
    useEffect(()=>{
        getMe()
            .then(() => setStatus('ok'))
            .catch(() => setStatus('fail'));
    },[]);
    if(status == 'loading') return <div>로딩중. . .</div>;
    if(status == 'fail') return <Navigate to ="/login" />;
    return <>{children}</>;

}
export default PrivateRoute;
