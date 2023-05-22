/** @format */

import Vue from "vue";
import VueRouter from "vue-router";

import MainView from "../views/MainView.vue";
import RealEstate from "@/views/RealEstateView";

// board
import BoardView from "@/views/BoardView.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

// notice
import NoticeView from "@/views/NoticeView.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";

// news
import NewsList from "@/components/news/NewsList";

// dashboard
// import LineChart from "@/components/dashboard/LineChart.vue";
// import MapChart from "@/components/dashboard/MapChart.vue";
// import PieChart from "@/components/dashboard/PieChart.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/land",
    name: "land",
    component: RealEstate,
  },
  {
    path: "/board",
    name: "board",
    redirect: "/board/list",
    component: BoardView,
    children: [
      {
        path: "list",
        name: "boardlist",
        component: BoardList,
      },
      {
        path: "detail/:board_no",
        name: "boarddetail",
        component: BoardDetail,
      },
      {
        path: "write",
        name: "boardwrite",
        component: BoardWrite,
      },
      {
        path: "update/:board_no",
        name: "boardupdate",
        component: BoardUpdate,
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticelist",
        component: NoticeList,
      },
      {
        path: "detail/:notice_no",
        name: "noticedetail",
        component: NoticeDetail,
      },
      {
        path: "write",
        name: "noticewrite",
        component: NoticeWrite,
      },
      {
        path: "update/:notice_no",
        name: "noticeupdate",
        component: NoticeUpdate,
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView"),
    redirect: "/user/login",
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserJoin"),
      },
      {
        path: "findpassword",
        name: "findpassword",
        component: () => import("@/components/user/UserFindPassword.vue"),
      },
      {
        path: "joinagreement",
        name: "joinagreement",
        component: () => import("@/components/user/UserJoinAgreement"),
      },
    ],
  },
  {
    path: "/news",
    name: "news",
    component: NewsList,
  },
  {
    path: "/dashboard",
    name: "dashboard",
    component: () => import("@/views/DashBoard.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
