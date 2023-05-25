<template>
	<div>
		<div class="inner-box">
			<div class="back" @click="() => this.$router.go(-1)">
				<v-icon>mdi-arrow-left</v-icon>
				뒤로가기
			</div>
			<div class="main">
				<!-- title -->
				<div>
					<span class="title">{{ notice.title }}</span>
				</div>
				<!-- meta-data -->
				<div class="meta">
					<span style="margin-right: 10px">관리자</span>
					<span v-if="notice.updateDate == null">{{ notice.createDate }}</span>
					<span v-else> {{ notice.updateDate }} (수정됨) </span>
					<span style="float: right">조회수 {{ notice.hit }}</span>
				</div>
				<hr />
				<!-- 본문 영역 -->
				<div class="content">
					<textarea
						class="w-100 h-100"
						style="resize: none"
						v-model="notice.content"
						readonly
						disabled
					></textarea>
				</div>
				<!-- update/delete -->
				<!-- 관리자만 사용가능(유저 개발 끝나면 적용) -->
				<div class="ud" v-if="this.userinfo == 'admin'">
					<span class="ud-content" @click="update()" style="cursor: pointer">수정</span> |
					<span class="ud-content" @click="remove()" style="cursor: pointer">삭제</span>
				</div>
				<hr />
			</div>
		</div>
	</div>
</template>
<script>
import { detail, remove } from "@/api/notice.js";

export default {
	name: "noticeDetail",
	components: {},
	data() {
		return {
			userinfo: "", // 오류메시지 제거용
			notice: {},
			comments: [],
			noticeNo: this.$route.params.notice_no,
			comment: {
				noticeNo: 0,
				comment: "",
				email: "",
				commentId: 0,
			},
		};
	},
	created() {
		detail(this.noticeNo, ({ data }) => {
			this.notice = data.notice;
			this.comments = data.comments;
		});
	},
	methods: {
		update() {
			this.$router.push(`/notice/update/${this.noticeNo}`);
		},
		remove() {
			remove(this.noticeNo, ({ data }) => {
				if (confirm("삭제하시겠습니까?")) {
					if (data === "success") {
						alert("삭제가 완료되었습니다.");
						this.$router.push("/notice");
					} else {
						alert("삭제에 실패했습니다.");
					}
				}
			});
		},
	},
};
</script>

<style scoped>
.inner-box {
	margin: auto;
	margin-top: 100px;
	margin-bottom: 100px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	min-width: 900px;
	max-width: 1200px;
	border-radius: 4px;
	border: solid 1px #d9d9d9;
	padding: 30px 100px 100px 30px;
}
.back {
	cursor: pointer;
	color: #939393;
}
.back:hover {
	color: #939393;
}
.main {
	margin-top: 60px;
	margin-left: 60px;
	padding: 0;
}
.title {
	font-size: 28px;
	font-weight: bold;
	width: 100%;
}
.meta {
	font-size: 14px;
	color: #939393;
	margin: 10px 0 5px 10px;
}
.content {
	height: 500px;
	padding: 20px;
	font-size: 18px;
}

.ud {
	text-align: right;
	font-size: 14px;
	margin-right: 5px;
	margin-bottom: 5px;
	color: #939393;
}
.ud-content {
	text-decoration: none;
	padding: 5px;
	cursor: pointer;
}
.ud-content:hover {
	color: #6f6f6f;
}

hr {
	background: #d9d9d9;
	height: 1px;
	border: 0;
	display: block;
}
</style>
