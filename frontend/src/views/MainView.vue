<template>
	<div id="main">
		<div id="main-page-image-container">
			<div id="text-box">
				<p id="main-text">어떠한 집을 찾고 있나요?</p>
			</div>
			<img src="@/assets/images/main-page-pic.png" alt="" />
			<div id="select-area-input-box" data-app>
				<v-select v-model="selectSido" :items="sidoList" item-text="name" item-value="sidoCode" menu-props="auto"
					label="시도 검색" hide-details outlined return-object color="#6750A4" background-color="#E0D0F4"></v-select>
				<span class="mx-1"></span>
				<v-select v-model="selectSigungu" :items="sigunguList" item-text="name" item-value="value" menu-props="auto"
					label="시군구 검색" hide-details outlined return-object color="#6750A4" background-color="#E0D0F4"></v-select>
				<span class="mx-1"></span>
				<v-select v-model="selectDong" :items="dongList" item-text="name" item-value="value" menu-props="auto"
					label="동 검색" hide-details outlined return-object color="#6750A4" background-color="#E0D0F4"></v-select>
			</div>
		</div>
		<!-- 공지사항, 자유게시판, 부동산뉴스 시작-->
		<div id="icon-navbar">
			<router-link to="/notice/list">
				<div>
					<img src="@/assets/icon/notice_icon.png" alt="공지 게시판" id="noticeIcon">
					<label for="noticeIcon">공지사항</label>
					<p>공지사항 바로가기</p>
				</div>
			</router-link>
			<router-link to="/board/list">
				<div>
					<img src="@/assets/icon/board_icon.png" alt="자유 게시판" id="boardIcon">
					<p>자유 게시판 바로가기</p>
				</div>
			</router-link>
			<router-link to="/news">
				<div>
					<img src="@/assets/icon/news_icon.png" alt="뉴스" id="newsIcon">
					<p>뉴스 바로가기</p>
				</div>
			</router-link>
		</div>
		<!-- 좋아요 많은 아파트, 좋아요 많은 거래 상위 5개 출력 -->
		<div id="main-stats-box">
			<!-- 아파트 랭킹 출력 -->
			<div id="aptContainer">
				<h3>인기 아파트 Top 5</h3>
				<table>
					<caption>인기 아파트 Top 5</caption>
					<thead>
						<th>랭킹</th>
						<th>주소</th>
						<th>아파트 이름</th>
						<th>평균 면적</th>
						<th>평균 가격</th>
						<th>좋아요 수</th>
					</thead>
					<tbody>
						<tr v-for="(apt, index) in topApts" v-bind:key="apt">
						<td>{{index+1}}</td>
						<td>{{apt.sidoName}} {{apt.gugunName}} {{apt.jibunAddress}}</td>
						<td>{{ apt.aptName }}</td>
						<td>{{ apt.areaAvg }}m2</td>
						<td>{{ apt.priceAvg }}(만원)</td>
						<td>{{ apt.likeNums }}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div id="dealContianer">
			<!-- 거래 랭킹 출력 -->
			<table>
				<caption>인기 거래 Top 5</caption>
					<thead>
						<th>랭킹</th>
						<th>주소</th>
						<th>아파트 이름</th>
						<th>층수</th>
						<th>면적</th>
						<th>가격</th>
						<th>좋아요 수</th>
					</thead>
					<tbody>
						<tr v-for="(deal, index) in topDeals" v-bind:key="deal">
						<td>{{index+1}}</td>
						<td>{{deal.sidoName}} {{deal.gugunName}} {{deal.jibunAddress}}</td>
						<td>{{ deal.aptName }}</td>
						<td>{{ deal.floor }}층</td>
						<td>{{ deal.area }}m2</td>
						<td>{{ deal.price }}(만원)</td>
						<td>{{ deal.likeNums }}</td>
						</tr>
					</tbody>
				</table>
		</div>
	</div>
</template>

<script>
import axios from "axios";

export default {
	name: "MainView",
	components: {},
	data() {
		return {
			selectSido: "시도 검색",
			sidoList: [""],
			selectSigungu: String,
			sigunguList: [""],
			selectDong: String,
			dongList: [""],
			topApts: [],
			topDeals: [],
		};
	},
	created() {
		let config = {
			method: "get", // 기본값
			baseURL: "http://localhost:9999/house/sido",
		};
		axios(config)
			.then(({ data }) => {
				console.log(data);
				this.sidoList = data.sidoList;
			})
			.catch((err) => {
				console.log(err);
			});

		config = {
			method: "get",
			baseURL: "http://localhost:9999/house/topapt",
		}
		axios(config)
			.then(({ data }) => {
				if (data.message == "success") { 
					this.topApts = data.houses;
					console.log(this.topApts);
				}
			})
			.catch((err) => {
				console.log(err)
			});			

		config = {
			method: "get",
			baseURL: "http://localhost:9999/house/topdeal",
		}
		axios(config)
			.then(({ data }) => {
				if (data.message == "success") { 
					this.topDeals = data.deals;
					console.log(this.topDeals);
				}
			})
			.catch((err) => {
				console.log(err)
			});		

	},
	methods: {},
	watch: {
		selectSido({ sidoCode }) {
			let sido = sidoCode;
			// console.log(sido);

			let config = {
				method: "get", // 기본값
				baseURL: "http://localhost:9999/house/sigungu",
				params: {
					sido,
				},
			};
			axios(config)
				.then(({ data }) => {
					// console.log(data.sidoList);
					this.sigunguList = data.sidoList;
				})
				.catch((err) => {
					console.log(err);
				});
		},
		selectSigungu({ sigunguCode }) {
			let sigungu = sigunguCode;
			let config = {
				method: "get", // 기본값
				baseURL: "http://localhost:9999/house/dong",
				params: {
					sigungu,
				},
			};
			axios(config)
				.then(({ data }) => {
					// console.log(data.dongList);
					this.dongList = data.dongList;
				})
				.catch((err) => {
					console.log(err);
				});
		},
		selectDong({ dongCode }) {
			console.log(dongCode);
		},
	},
};
</script>

<style scoped>
* {
	font-family: 'Noto Sans';
	font-style: normal;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: #6960d5;
}

#main {
	height: 650px;
	width: 100%;
	text-align: center;
}

#main-page-image-container {
	/* border: 1px black solid; */
	position: relative;
	height: 100%;
}

#main-page-image-container img {
	width: 100%;
	height: 100%;
	filter: opacity(50%);
}

#text-box {
	width: 100%;
	position: absolute;
	top: 250px;
	z-index: 1;
	/* left: 50%;
	transform: translate(-50%, 0); */
	display: inline-block;
}

#main-text {
	color: #ffffff;
	font-weight: bold;
	font-size: 50px;
}

#select-area-input-box {
	/* border: 1px black solid; */
	position: absolute;
	width: 700px;
	height: 72px;
	top: 436px;
	left: 50%;
	transform: translate(-50%, 0);
	display: flex;
}

#icon-navbar {
	width: 100%;
	height: 250px;
	/* border: 1px black solid; */
}

#main-stats-box {
	width: 100%;
	height: 400px;
	/* border: 1px black solid; */
}

#newsIcon,
#noticeIcon,
#boardIcon {
	display: inline-flex;
	position: inherit;
	width: 10%;
}
</style>
