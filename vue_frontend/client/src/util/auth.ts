import axios from 'axios';
const isAuthenticated = async ():Promise<boolean>=>{
    axios.defaults.withCredentials = true;
    axios.defaults.withXSRFToken = true;
    const response:boolean = await axios
        .get("http://localhost:8080/auth/authenticated",
        )
        .then(response => {
            const data = response.data;
            if(data.status !== "true"){
                return false;
            }
            return true;
        })
        .catch(() =>{
            return false;
        })
    return response;
}

const login = async (username,password):Promise<{}>=>{
    const response = await axios
        .post("http://localhost:8080/auth/login",
            {
                login:username,
                password:password
            },
            {
                withCredentials:true
            }
        )
        .then(response => {
            const data = response.data;
            if(!data.status){
                alert("Cringe");
                return null;
            }
            return data;
        })
        .catch(() =>{
            return null;
        })
    return response;
}

const signup = async (username:string,email:string,password:string)=>{
    const response = await axios
        .post("http://localhost:8080/auth/signup",{
            username:username,
            email:email,
            password:password
        })
        .then(response => {
            const data = response.data;
            if(!data) {
                return {status:"failed",error:"unknown"}
            }
            return response.data;

        })
        .catch((error) =>{
            return {status:"failed",error:error}
        })
    return response;
}
export {isAuthenticated,login,signup}
