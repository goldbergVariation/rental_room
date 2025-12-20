#  MyRental

賃貸物件検索・管理ができる Web アプリケーション（JSP/Servlet + PostgreSQL）

##  公開URL（Render）

### ゲスト・利用者 用

### ▶ 利用者 (テストアカウント)   
**ID：** user  

**パスワード：** password

[https://rental-room-x317.onrender.com/rental_room/guest/top.jsp](https://rental-room-x317.onrender.com/rental_room/guest/top.jsp)

### ▶ 管理者 (テストアカウント)　 
**ID：** manager

**パスワード：** password

[https://rental-room-x317.onrender.com/rental_room/guest/manager_login.jsp](https://rental-room-x317.onrender.com/rental_room/guest/manager_login.jsp)

##  概要（Overview）

このWebアプリ（MyRental） は、千葉市の賃貸物件を対象とした
検索・閲覧・レビュー投稿・管理ができる
Java（JSP / Servlet）ベースの Web アプリケーションです。

職業訓練校の総合製作として開発したアプリをベースに、
製作発表会後もリファクタリングや機能追加を継続しており、
現在も改善を続けています。

ソースコードは GitHub で管理し、
デプロイは Render（Docker）上で行い、
データベースには PostgreSQL を使用しています。

---

##  機能一覧（Features）

###  ゲスト

* 物件検索
* 物件詳細ページ・口コミ表示
* 利用者新規登録 / ログイン

###  利用者

* 物件一覧・検索
* 物件詳細ページ・口コミ表示
* レビュー投稿（1〜300文字）
* 退会
* 口コミの一覧表示・削除

###  管理者

* 物件一覧・検索
* 物件詳細ページ・口コミ表示
* ログイン
* 物件登録
* 掲載状態変更（掲載停止 / 再掲載）
* 管理者新規登録

##  使用技術
### ▶ ローカル開発環境

```
言語：Java（JSP & Servlet）, Jakarta EE , JSTL
データベース：MySQL 8.0.44
サーバー：Apache Tomcat 10.1.49
IDE：Eclipse（Pleiades）
設計：MVC / Front Controller / DAO / JavaBeans
```

### ▶ 本番環境（Render）

```
Render Web Service（Docker Deploy）
アプリサーバー：Tomcat 10.1（JDK 21）
デプロイ形式：WAR（Maven Multi-stage Build）
データベース：PostgreSQL 17.7（Render PostgreSQL）
```

---

##  このアプリのポイント（Highlights）

```
MVC + FrontController による拡張性
DAOパターンによる DB アクセス分離
PreparedStatement による SQLインジェクション対策
JSTL <c:out> による XSS対策
ログインパスワードを暗号化したハッシュ値（Bcrypt使用）で保存
PostgreSQL で画像名などを厳密管理（大文字小文字区別）
ローカル（MySQL/Tomcat）と本番（PostgreSQL/Docker/Tomcat）の両対応
```
---
##  ディレクトリ構成

```
rental_room/
├─ src/
│  └─ main/
│      ├─ java/
│      │   ├─ bean
│      │   ├─ dao
│      │   ├─ guest
│      │   ├─ manager
│      │   ├─ tool
│      │   └─ user
│      └─ webapp/
│          ├─ common
│          ├─ css
│          ├─ guest
│          ├─ images
│          ├─ manager
│          ├─ user
│          ├─ META-INF
│          └─ WEB-INF/
├─ Dockerfile
├─ pom.xml
└─ README.md
```

---

##  Dockerfile（Render 本番環境）

````markdown
```dockerfile
# ---- 1. Maven Build Stage ----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- 2. Tomcat Deploy Stage ----
FROM tomcat:10.1-jdk21
COPY --from=build /app/target/rental_room-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/rental_room.war

ENV PORT=8080
EXPOSE ${PORT}

CMD ["sh", "-c", "catalina.sh run"]
```
````

---

##  データベース構成

```
users
  利用者情報を管理します。

managers
  管理者アカウント情報を管理します。

properties
  賃貸物件情報を管理します。

reviews
  口コミ情報を管理します（1～300文字）。

■ 設計上のポイント

・口コミは「1利用者につき、1物件に対して1件まで」とし、
  （property_id, user_id）の複合ユニーク制約により実現しています。

・利用者の退会や物件の掲載停止は、
  データの履歴を保持するため物理削除ではなく論理削除を採用しています。

・ログインパスワードは Bcrypt によるハッシュ化を行い、
  ハッシュ値（最大60文字）を保存しています。
```


---

##  セットアップ方法（How to Run）

### ▶ ローカル開発（MySQL ＋ Tomcat）

```
1. データベース作成
CREATE DATABASE rental_room CHARACTER SET utf8mb4;

2. 初期データ import
mysql -u root -p rental_room < dump.sql

3. 実行
Eclipse → Run on Server（Tomcat）
```

### ▶ 本番環境（Render / PostgreSQL）

Render は GitHub リポジトリと連携することで、
Dockerfile に従って自動でビルド・デプロイを行います。

PostgreSQL 初期化例：

```
psql -h <Renderホスト> -U <ユーザー名> -d rental_room -f dump_postgres.sql
```



---

##  今後の改善予定

```
管理者による口コミの削除
登録された物件の削除
ログイン履歴の保持・表示	
利用者による物件のお気に入り保存
利用者一覧の取得
物件検索の種類とソートの強化

```

##  Screenshots

クリックしてください👇
<details>
<summary>▶ 一般ユーザー画面</summary>

### トップページ  

<img width="960" height="538" alt="一般トップ画面" src="https://github.com/user-attachments/assets/17e51f2e-7154-43f2-a550-1cbce20b8184" />

### 物件一覧  

<img width="952" height="527" alt="物件検索結果" src="https://github.com/user-attachments/assets/08ed61a7-323f-4721-8d14-dd68cffe2c77" />

### 物件詳細  

<img width="656" height="539" alt="検索結果詳細" src="https://github.com/user-attachments/assets/cb08312b-366f-4e56-a4d5-80b4f9758c2e" />

</details>

<details>
<summary>▶ 管理者画面</summary>

### 管理者トップ  

<img width="747" height="276" alt="管理者トップ画面" src="https://github.com/user-attachments/assets/909481d8-b637-4ee1-b188-9d66fda6162f" />

</details>

<details>
<summary>▶ ER図 / ユースケース図</summary>

### ER図  

<img width="551" height="384" alt="ER図" src="https://github.com/user-attachments/assets/74234482-2b54-41c1-96c9-83d739d8707c" />

### ユースケース図  

<img width="700" height="457" alt="ユースケース図" src="https://github.com/user-attachments/assets/d1aed35d-c192-4b78-94d6-d86fcfa78ddb" />

</details>




