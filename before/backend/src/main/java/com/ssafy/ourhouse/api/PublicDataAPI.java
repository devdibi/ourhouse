package com.ssafy.ourhouse.api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/public", produces = "application/json; charset=utf8")
public class PublicDataAPI {

    @GetMapping("/business")
    public String getBusinessDistrict() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInRadius"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=jxxl3rLm%2FskTLPONvQj6KXkjSZlpHSCuH8JPt9ueN49v0P0hU68Wew6dcM2ihtRP%2BQsUHpOGexuUVwl9XyOwBw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("500", "UTF-8")); /* 반경입력, (미터단위, 최대2000 미터)*/
        urlBuilder.append("&" + URLEncoder.encode("cx","UTF-8") + "=" + URLEncoder.encode("128.560504506168", "UTF-8")); /* 원형의 중심점의 경도값으로 WGS84 좌표계 사용*/
        urlBuilder.append("&" + URLEncoder.encode("cy","UTF-8") + "=" + URLEncoder.encode("35.9443956399221", "UTF-8")); /* 원형의 중심점의 위도값으로 WGS84 좌표계 사용*/
        urlBuilder.append("&" + URLEncoder.encode("indsLclsCd","UTF-8") + "=" + URLEncoder.encode("Q", "UTF-8")); /* 입력된 대분류 업종에 해당하는 것만 조회*/
        urlBuilder.append("&" + URLEncoder.encode("indsMclsCd","UTF-8") + "=" + URLEncoder.encode("Q12", "UTF-8")); /* 입력된 중분류 업종에 해당하는 것만 조회*/
        urlBuilder.append("&" + URLEncoder.encode("indsSclsCd","UTF-8") + "=" + URLEncoder.encode("Q12A01", "UTF-8")); /* 입력된 소분류 업종에 해당하는 것만 조회*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* xml / json*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return sb.toString();
    }
}
