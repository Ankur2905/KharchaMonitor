import axios from "axios";
import { store } from "../store";

const productionUrl = "http://localhost:8080/api";

export const customFetch = axios.create({
  baseURL: productionUrl,
});

customFetch.interceptors.request.use((config) => {
  const state = store.getState();
  const token = state.userState.token || localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});
