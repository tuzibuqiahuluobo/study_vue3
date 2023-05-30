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
            component:()=>import('../views/login/Login.vue'),
            // children:[
            //     {
            //         path:"/components/register",
            //         name:"Register",
            //         component:()=>import('../views/login/components/RegisterPage.vue')
            //     }
            // ]
        },
        {
            path:"/sign_out",
            name:"Sign_out",
            component:()=>import('../views/login/components/Sign_out.vue')
        },
        {
            path:"/register",
            name:"Register",
            component:()=>import('../views/login/components/RegisterPage.vue')
        },
        {
            path:"/reset_password",
            name:"Reset_password",
            component:()=>import('../views/login/components/Reset_password.vue')
        }

    ]
})

export default router;