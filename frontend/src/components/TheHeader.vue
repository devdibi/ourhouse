<template>
  <header>
    <div>
      <b-navbar toggleable="md" type="dark" class="purple">
        <b-navbar-brand to="/" tag="h1">우리의 집</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-5">
            <!-- View Nav -->
            <b-nav-item to="/land" link-classes="text-white">지도 검색</b-nav-item>
            <b-nav-item link-classes="text-white" @click="moveNotice()">공지 사항</b-nav-item>
            <b-nav-item link-classes="text-white" @click="moveBoard()">자유 게시판</b-nav-item>

            <!-- 매매 좋아요 Nav -->
            <b-nav-item-dropdown text="내 목록" toggle-class="text-white">
              <b-dropdown-group id="dropdown-group-1" header="부동산">
                <b-dropdown-item href="#">관심 단지</b-dropdown-item>
                <b-dropdown-item href="#">관심 매물</b-dropdown-item>
              </b-dropdown-group>
              <b-dropdown-divider></b-dropdown-divider>
              <b-dropdown-item href="#">마이 페이지</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>

          <!-- 유저부분 Nav -->
          <b-navbar-nav class="ml-auto">
            <b-nav-item @click="moveLogin()" link-classes="text-white">로그인</b-nav-item>
            <b-nav-item @click="moveJoin()" link-classes="text-white">회원 가입</b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
  </header>
</template>

<script>
export default {
  name: "HeaderView",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    moveNotice() {
      this.$router.push("/notice");
    },
    moveBoard() {
      this.$router.push("/board");
    },
    moveLogin() {
      this.$router.push("/user/login");
    },
    moveJoin() {
      this.$router.push("/user/join");
    },
  },
};
</script>

<style scoped>
.purple {
  background: rgba(67, 56, 202, 0.8);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.2);
}
</style>
