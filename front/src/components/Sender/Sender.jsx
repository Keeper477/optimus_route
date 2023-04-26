import Axios from "axios";

const api = Axios.create({
    baseURL: '/api/',
});

const Sender = {
    sendPoints: (points) => {
        let rq = {
            startPoint: points[0],
            points: points.slice(1, -1),
            endPoint: points[points.length - 1]
        }
        return api.post(`send`, rq);
    }
}


export default Sender;