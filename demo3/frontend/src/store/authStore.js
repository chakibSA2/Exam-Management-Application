import { reactive, computed } from 'vue';

export const authStore = reactive({
    user: null,

    login(user) {
        this.user = user;
        localStorage.setItem('user', JSON.stringify(user));
    },

    logout() {
        this.user = null;
        localStorage.removeItem('user');
    },

    initialize() {
        const user = localStorage.getItem('user');
        if (user) {
            this.user = JSON.parse(user);
        }
    },

    hasRole(role) {
        return computed(() => this.user?.role === role);
    }
});

authStore.initialize();
