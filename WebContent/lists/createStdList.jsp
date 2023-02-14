<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
    <title>스터디 생성</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="stdlist.css" />

</head>

<script type="text/javascript">
    function checkValid() {
        var f = window.document.writeForm;

        if (f.roomTitle.value == "") {
            alert("스터디 이름을 입력해 주세요.");
            return false;
        }
        if (f.category.value == "") {
            alert("스터디 타입을 선택해 주세요.");
            return false;
        }

        return true;
    }
</script>


<body class="is-preload">
    <section class="wrapper style2 align-center">
        <div class="inner">
            <div class="index align-left">
                <!-- 내용 -->
                <section>
                    <div class="content">
                        <form name="writeForm" action="StdList/insertList" method="post" accept-charset="UTF-8" onSubmit='return checkValid()'>
                            <div class="fields">
                                <div class="field">
                                    <table border="1" cellspacing="1" width="100%">
                                        <tr>
                                            <td>스터디등급</td>
                                            <td>
                                                <ul class="actions">
                                                    <li><a href="#" class="button primary" name=grade>premium</a></li>
                                                    <li><a href="#" class="button" name=grade>free</a></li>
                                                </ul>
                                            </td>
                                </div>
                                <tr>
                                    <td>스터디 이름</td>
                                    <td>
                                        <div class="content">
                                            <div class=fields>
                                                <div class="field">
                                                    <label for="roomTitle" /> <input type="text" name="roomTitle" id="roomTitle" placeholder="스터디 이름을 입력하세요." />
                                                </div>
                                            </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>스터디 타입</td>
                                    <td>
                                        <div class="content">
                                            <div class="field">
                                                <label for="category" /> <select name="category" id="category">
                                                    <option value="">타입을 지정해주세요.</option>
                                                    <option value="자격증">자격증</option>
                                                    <option value="수능">수능</option>
                                                    <option value="취업">취업</option>
                                                    <option value="자율">자율</option>
                                                </select>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>상세설명</td>
                                    <td>
                                        <div class="content">
                                            <div class="field">
                                                <label for="roomDesc" />
                                                <textarea name="roomDesc" id="roomDesc" rows="6" placeholder="스터디 상세 설명을 입력하세요."></textarea>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>최대 가입 인원</td>
                                    <td>
                                        <div class="content">
                                            <div class="field">
                                                <label for="maxMem" /> <select name="maxMem" id="maxMem">
                                                    <option value="4">4</option>
                                                    <option value="8">8</option>
                                                </select>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>스터디 비밀번호</td>
                                    <td>
                                        <div class="content">
                                            <div class=fields>
                                                <div class="field">
                                                    <label for="roomPw" /> <input type="password" name="roomPw" id="roomPw" placeholder="스터디 비밀번호를 입력하세요." />
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>

                                </table>

                                <div class='align-center'>


                                    <input type="submit" value="스터디 만들기" class="button primary fit">
                                </div>

                        </form>
                    </div>
                </section>

</body>
</html>
