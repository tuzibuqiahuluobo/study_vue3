import { createRouter, createWebHistory } from "vue-router";



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
            path: "/index",
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
            path:"/",
            name:"Login",
            component:()=>import('../views/login/Login.vue')
        },
        {
            path:"/Sign_out",
            name:"sign_out",
            component:()=>import('../views/login/components/Sign_out.vue')
        }
    ]
})

export default router;