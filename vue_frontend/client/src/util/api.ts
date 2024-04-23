import axios from 'axios';

const test = async () =>{
    axios.defaults.withXSRFToken = true;
    const response:boolean = await axios
        .get("http://localhost:8080/api/test",
        )
        .then(response => {
            const data = response.data;
            return data;
        })
        .catch(() =>{
            return null;
        })
    return response;
};

const sendToSolve = async(matrix,startString,endString,algo) =>{

    axios.defaults.withXSRFToken = true;
    const response:boolean = await axios
        .post("http://localhost:8080/api/solve",
            {
                matrixJson:matrix,
                start:startString,
                end:endString,
                algorhitm:algo
            }
        )
        .then(response => {
            const data = response.data;
            return data;
        })
        .catch((error) =>{
            console.log(error);
            return null;
        })
    return response;
};

export {test,sendToSolve}
