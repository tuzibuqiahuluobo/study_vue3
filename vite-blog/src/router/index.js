import { createRouter, createWebHashHistory } from "vue-router";
import Home from '../views/Home.vue'
import Details from '../views/Details.vue'
import Create from '../views/Create.vue'
import Tag from '../views/Tag.vue'
import Index from '../views/index/Index.vue'

const routes = [
    { path: "/", name: "Index", component: Index },
    { path: "/posts/:id", name: "Details", component: Details, props: true },
    { path: "/create", name: "Create", component: Create },
    { path: "/tags/:tag", name: "Tag", component: Tag },
]


const router = createRouter({
    history: createWebHashHistory(),
    routes
})
export default router;