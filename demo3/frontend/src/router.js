import { createRouter, createWebHistory } from "vue-router";
import UsersView from "@/UsersView.vue";
import UserForm from "@/components/UserForm.vue";

const routes = [
    { path: "/", redirect: "/users" },
    { path: "/users", component: UsersView },
    { path: "/create-user", component: UserForm }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
