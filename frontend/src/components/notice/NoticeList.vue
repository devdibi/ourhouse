<template>
    <div id="notice">
        <h1>공지사항</h1>
        <table id="list">
            <colgroup>
                <col style="width:10%">
                <col style="width:50%">
                <col style="width:10%">
                <col style="width:10%">
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
                <tr v-for="notice in lists" :key="notice.noticeNo">
                    <td>{{ notice.noticeNo }}</td>
                    <td><router-link :to="{name:'noticedetail',params:{notice_no:notice.noticeNo}}">{{ notice.title }}</router-link></td>
                    <td>{{ notice.email }}</td>
                    <td >{{ notice.hit }}</td>
                    <td>{{ notice.createDate }}</td>
                </tr>
            </tbody>
        </table>
        <div>
            <!-- 페이지네이션 -->
            <table id="pagination">
                <tr>
                    <td @click="prev"><button>&lt;&lt;</button></td>
                    <td style="width:200px">{{ page_no }} 페이지</td>
                    <td @click="next"><button>&gt;&gt;</button></td>
                </tr>
                <router-link :to="{name:'noticewrite'}" style="float:right;verticla-align:middle"><v-btn>글 작성</v-btn></router-link>
            </table>
        </div>
    </div>
</template>

<script>
import { list } from "@/api/notice.js"

export default {
    name: 'NoticeList',
    components: {},
    data() {
        return {
            notices: [],
            lists: [],
            page_no: 1,
        };
    },
    created() {
        // 리스트 호출
        list((response) => {
            console.log(response.data.noticeList)
            this.notices = response.data.noticeList;

            this.lists = [];

            console.log(this.notices)
            console.log(this.lists)
            for (var p = 0 * 10; p < 10; p++){ 
                if(p == this.notices.length) return
                this.lists.push(this.notices[p]);
            }
        });
    },
    methods: {
        prev() {
            if (this.page_no == 1) {
                alert("첫 페이지입니다.")
                return
            }
            this.page_no = this.page_no - 1;

            this.lists = []

            for (var p = (this.page_no - 1) * 10; p < (this.page_no - 1) * 10 + 10; p++){
                this.lists.push(this.notices[p]);
            }
            console.log(this.page_no)
        },
        next() {
            if (this.page_no >= this.notices.length / 10) {
                alert("마지막 페이지입니다.")
                return
            }

            this.page_no = this.page_no + 1;

            this.lists = []

            for (var p = (this.page_no - 1) * 10; p < (this.page_no - 1) * 10 + 10; p++){
                if(p == this.notices.length) return
                this.lists.push(this.notices[p]);
            }

            console.log(this.page_no)
        }
    },
};
</script>

<style scoped>
#notice{
    text-align: center;
    margin:0 20% 0 20%;
}

h1{
    margin-top:100px; 
    margin-bottom:50px;
    text-align:center;
}
#list {
    width: 100%;
    table-layout: fixed;
    border-collapse:collapse;
    margin-bottom:50px;
    
}

#list tr{
    height:40px;
    border-bottom:solid 1px #d9d9d9;
    
}

#list th{
    padding-bottom:10px;
    text-align:center;
}

#pagination{
    display:inline;
    margin:auto;
    border-collapse:collapse;
}

#pagination td{
    width: 50px;
    height: 30px;
    padding: 6px 10px;
    border:solid 1px #d9d9d9
}
a{
    text-decoration: none;
    color:#000000;
    font-family:'Noto Sans';
}

</style>


