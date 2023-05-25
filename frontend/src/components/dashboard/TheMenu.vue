<template>
  <div id="frame" style="">
    <div>
      <div class="title">이름</div>
      {{ this.user.name }}
    </div>
    <div>
      <div class="title">이메일</div>
      {{ this.user.email }}
    </div>
    <div>
      <div class="title">관심지역</div>
    </div>
    <div class="area">{{ this.user.favoriteArea }}</div>
    <div>
      <div class="title">거주지역</div>
    </div>
    <div class="area">{{ this.user.dwellArea }}</div>
    <hr />
    <ul>
      <ol class="menu">
        <router-link :to="{ name: dashboard }" class="menu-item">대시보드</router-link>
      </ol>
      <ol class="menu">
        <router-link to="user/modify" class="menu-item">회원정보 열람 및 수정</router-link>
      </ol>
      <ol class="menu">
        <router-link to="user/likeapt" class="menu-item">관심 단지 목록</router-link>
      </ol>
      <ol class="menu">
        <router-link to="user/likedeal" class="menu-item">관심 매물 목록</router-link>
      </ol>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TheMenu",
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {},
  props: {
    user: Object,
  },
};
</script>

<style scoped>
#frame {
  width: 260px;
  height: 100%;
  border: none;
  background: linear-gradient(180deg, rgba(67, 56, 202, 0.8) 0%, rgba(67, 56, 202, 0.176) 100%);
  box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
  padding: 20px 20px 20px 20px;
  color: #ffffff;
}

a {
  color: #ffffff;
}

.area {
  font-size: 1.5vmin;
}

.title {
  font-size: 1.8vmin;
  color: rgba(232, 230, 255, 0.8);
}
.menu {
  margin-top: 20px;
}
.menu-item {
  font-size: 1.8vmin;
  text-decoration: none;
}

.menu-item:hover {
  color: #f2f2f2;
}
</style>
