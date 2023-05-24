<template>
  <div id="news">
    <h1>뉴스 게시판</h1>
    <table id="list">
      <colgroup>
        <col style="width: 50%" />
        <col style="width: 10%" />
        <col style="width: 20%" />
      </colgroup>
      <thead>
        <tr>
          <th>제목 및 요약</th>
          <th>조회수</th>
          <th>작성일자</th>
        </tr>
      </thead>
      <tbody>
        <tr class="trow" v-for="article in lists" :key="article.newsNo" @click="moveLink(article.link, article.newsNo)" style="cursor: pointer">
          <td style="padding: 10px">
            <div style="text-align: left; font-size: 18px; font-weight: bold">
              <span v-html="article.title"></span>
            </div>
            <div style="text-align: left; margin-left: 5px; color: #939393; font-size: 12px">
              <span v-html="article.context"></span>
            </div>
          </td>
          <td>{{ article.hit }}</td>
          <td>{{ article.postDate }}</td>
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
      </table>
      <!-- SpringSchedule 구현 완료 매일 9시마다 자동으로 실행 예정 -->
      <!-- <button class="crud" style="float: right" @click="newsUpdate()">불러오기</ㅍbutton> -->
    </div>
  </div>
</template>

<script>
import { list, update, hit } from "@/api/news.js";
export default {
  name: "NewsList",
  components: {},
  data() {
    return {
      userinfo: "",
      lists: [],
      news: [],
      page_no: 1,
    };
  },
  created() {
    list((response) => {
      this.news = response.data.newsList;

      // 처음 리스트 출력
      for (var p = 0 * 10; p < 10; p++) {
        if (p == this.news.length) return;
        this.lists.push(this.news[p]);
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
        this.lists.push(this.news[p]);
      }
    },
    next() {
      // 페이지 계산
      if (this.page_no >= this.news.length / 10) {
        alert("마지막 페이지입니다.");
        return;
      }

      // 페이지 번호 변경
      this.page_no = this.page_no + 1;
      // 출력할 페이지 초기화
      this.lists = [];

      // 출력할 페이지 입력
      for (var p = (this.page_no - 1) * 10; p < (this.page_no - 1) * 10 + 10; p++) {
        if (p == this.news.length) return;
        this.lists.push(this.news[p]);
      }
    },
    // detail 페이지로 이동
    moveLink(path, newsNo) {
      console.log(newsNo);
      window.open(path);
      hit(
        newsNo,
        (response) => {
          console.log(response);
        },
        (err) => {
          console.log(err);
          // alert("error 발생");
        }
      );
      list((response) => {
        this.news = response.data.newsList;

        this.lists = [];

        // 처음 리스트 출력
        for (var p = 0 * 10; p < 10; p++) {
          if (p == this.news.length) return;
          this.lists.push(this.news[p]);
        }
      });
    },
    // 뉴스 목록 강제 업데이트
    newsUpdate() {
      update((response) => {
        console.log(response);

        list((response) => {
          this.news = response.data.newsList;

          this.lists = [];

          // 처음 리스트 출력
          for (var p = 0 * 10; p < 10; p++) {
            if (p == this.news.length) return;
            this.lists.push(this.news[p]);
          }
        });
      });
    },
  },
};
</script>

<style scoped>
#news {
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
