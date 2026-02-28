import api from './axios';

export const signup =(email: string , password: string , name: string) =>{
    return api.post('api/auth/signup', {email,password,name})
};

export const login =(email:string ,password:string)=>{
    return api.post('/api/auth/login',{email,password});
};

export const getMe =() =>{
    return api.get('/api/auth/me');
};