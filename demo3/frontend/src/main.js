import { createApp } from "vue";
import App from "./views/App.vue";
import { createPinia } from "pinia";
import "bootstrap/dist/css/bootstrap.min.css";
import router from "./router.js";

const app = createApp(App);
app.use(router);
app.use(createPinia());
app.mount("#app");
