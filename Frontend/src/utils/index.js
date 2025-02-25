import axios from "axios";

const productionUrl = "http://localhost:8080/api";

export const customFetch = axios.create({
  baseURL: productionUrl,
});
