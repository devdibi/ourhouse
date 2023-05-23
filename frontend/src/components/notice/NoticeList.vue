<template>
  <div id="notice">
    <h1>공지사항</h1>
    <table id="list">
      <colgroup>
        <col style="width: 10%" />
        <col style="width: 50%" />
        <col style="width: 10%" />
        <col style="width: 20%" />
      </colgroup>
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>조회수</th>
          <th>작성일자</th>
        </tr>
      </thead>
      <tbody>
        <tr class="trow" v-for="notice in lists" :key="notice.noticeNo" @click="move(`detail/${notice.noticeNo}`)" style="cursor: pointer">
          <td>{{ notice.noticeNo }}</td>
          <td>{{ notice.title }}</td>
          <td>{{ notice.hit }}</td>
          <td>{{ notice.createDate }}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <!-- 페이지네이션 -->
      <table id="pagination">
        <tr>
          <td @click="prev" class="paging">&lt;&lt;</td>
          <td style="width: 150px; border: none">{{ page_no }} 페이지</td>
          <td @click="next" class="paging">&gt;&gt;</td>
        </tr>
        <!-- 관리자만 사용가능(유저 개발 끝나면 적용) -->
        <router-link :to="{ name: 'noticewrite' }" style="float: right; verticla-align: middle" v-if="this.userinfo == 'admin'">
          <button class="crud">글 작성</button>
        </router-link>
      </table>
    </div>
  </div>
</template>

<script>
import { list } from "@/api/notice.js";

export default {
  name: "NoticeList",
  components: {},
  data() {
    return {
      userinfo: "", // 오류메시지 제거용
      notices: [],
      lists: [],
      page_no: 1,
    };
  },
  created() {
    // 리스트 호출
    list((response) => {
      this.notices = response.data.noticeList;

      // 처음 리스트 출력
      for (var p = 0 * 10; p < 10; p++) {
        if (p == this.notices.length) return;
        this.lists.push(this.notices[p]);
      }
    });
  },
  methods: {
    prev() {
      // 출력할 페이지 계산
      if (this.page_no == 1) {
        alert("첫 페이지입니다.");
        return;
      }
      // 페이지 번호 변경
      this.page_no = this.page_no - 1;

      // 출력할 페이지 초기화
      this.lists = [];

      // 출력할 페이지 입력
      for (var p = (this.page_no - 1) * 10; p < (this.page_no - 1) * 10 + 10; p++) {
        this.lists.push(this.notices[p]);
      }
    },
    next() {
      // 페이지 계산
      if (this.page_no >= this.notices.length / 10) {
        alert("마지막 페이지입니다.");
        return;
      }

      // 페이지 번호 변경
      this.page_no = this.page_no + 1;
      // 출력할 페이지 초기화
      this.lists = [];

      // 출력할 페이지 입력
      for (var p = (this.page_no - 1) * 10; p < (this.page_no - 1) * 10 + 10; p++) {
        if (p == this.notices.length) return;
        this.lists.push(this.notices[p]);
      }
    },
    // detail 페이지로 이동
    move(path) {
      this.$router.push(path);
    },
  },
};
</script>

<style scoped>
#notice {
  text-align: center;
  margin: 0 10% 0 10%;
}

h1 {
  margin-top: 100px;
  margin-bottom: 50px;
  text-align: center;
}
#list {
  width: 100%;
  table-layout: fixed;
  border-collapse: collapse;
  margin-bottom: 50px;
}
.trow:hover {
  background-color: #f2f2f2;
}

#list tr {
  height: 40px;
  border-bottom: solid 1px #d9d9d9;
}

#list th {
  padding-bottom: 10px;
  text-align: center;
}

#pagination {
  display: inline;
  margin: auto;
  border-collapse: separate;
}

#pagination td {
  width: 50px;
  height: 30px;
  padding: 6px 10px;
  border: solid 1px #d9d9d9;
}

.paging {
  cursor: pointer;
  border-color: #d9d9d9;
  border-radius: 4px;
}
.paging:hover {
  background-color: #f2f2f2;
  border-color: #f2f2f2;
}

.crud {
  border: none;
  background-color: rgba(67, 56, 202, 0.8);
  color: #ffffff;
  width: 80px;
  height: 35px;
  border-radius: 4px;
  font-size: 15px;
  box-shadow: 0px 0px 3px 0px black;
}

.crud:hover {
  background-color: rgba(67, 56, 202, 0.9);
}
</style>
