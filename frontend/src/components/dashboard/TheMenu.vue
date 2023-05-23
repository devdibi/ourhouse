<template>
  <div id="frame" style="">
    <div>이름</div>
    <div>이메일</div>
    <div>관심지역</div>
    <div>거주지역</div>
    <router-link to="user/modify">회원정보 수정</router-link>
    <hr />
    <div>대시보드</div>
    <div>회원정보 수정</div>
    <div>관심 단지 목록</div>
    <div>관심 매물 목록</div>
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
</style>
