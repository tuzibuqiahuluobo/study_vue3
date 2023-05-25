import { createRouter, createWebHistory } from "vue-router";

import Details from '../views/Details.vue'
import Create from '../views/Create.vue'
import Tag from '../views/Tag.vue'
import Index from '../views/index/Index.vue'
import Login from "../views/index/components/Login.vue";


// const routes = [
//
//
//     { path: "/posts/:id", name: "Details", component: Details, props: true },
//     { path: "/create", name: "Create", component: Create },
//     { path: "/tags/:tag", name: "Tag", component: Tag },
// ]

const router = createRouter({
    history: createWebHistory(),
    routes:[
        {
            path: "/",
            name: "Index",
            component: ()=>import('../views/index/Index.vue') ,
            // children:[
            //     {
            //         path :"/Login",
            //         name:"index_login",
            //         component:()=>import('../views/index/components/Login.vue')
            //     }
            // ]
        },
        {
            path:"/Login",
            name:"Login",
            component:()=>import('../views/index/components/Login.vue')
        }
    ]
})

export default router;