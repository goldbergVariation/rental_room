## 🏠 MyRental

```
賃貸物件検索・管理ができるWebアプリケーション（JSP/Servlet + MySQL）
```
## 📌 概要（Overview）

MyRental は、賃貸物件の検索・閲覧・レビュー投稿・管理を行える
Java（JSP & Servlet）ベースのWebアプリケーションです。
職業訓練校の総合製作として開発しました。

ユーザー向け（ゲスト / 利用者）と
管理者向け（管理者）の画面を実装し、
物件閲覧〜レビュー〜管理までを一通り体験できる仕組みになっています。

## 🛠 使用技術（Tech Stack）
```
分類	ローカル開発技術
言語	Java（JSP ＆ Servlet）
フレームワーク	Jakarta EE / JSTL
データベース	MySQL 8.0.44
サーバー	Apache Tomcat 10.1.49
IDE	Eclipse（Pleiades）
バージョン管理	GitHub
その他	HTML / CSS / JDBC / Java Beans / DAOパターン / MVCパターン / Front Controllerパターン

📡 デプロイ環境（Deployment）

Render Web Service（Java 17）

ビルド＆実行：Render Java Buildpack

アプリケーション形式：WAR/JAR

データベース：Render PostgreSQL 17

ローカル開発：Eclipse + Tomcat 10.1.x（職業訓練校環境）

データベース	PostgreSQL 17.7
🎮 機能一覧（Features）
👤 一般ユーザー（ゲスト/会員）

物件一覧・検索表示

物件詳細の閲覧

レビュー投稿機能（1〜300文字）

会員登録 / ログイン

マイページでのレビュー管理

🛒 管理者（manager）

物件登録・編集・削除

掲載状態の変更（空室 / 掲載停止 など）

👑 スーパ管理者（super admin）

管理者とユーザーの管理（削除・凍結）

すべてのレビューと物件の参照

売上管理（※必要なら後で追記）

## 📁 ディレクトリ構成（Directory Structure）

```
rental_room/
├─ src/
 └─ main/
      ├─ java/
      │   ├─ bean
      │   ├─ dao
      │   ├─ guest
      │   ├─ manager
      │   ├─ tool
      │   └─ user
      └─ webapp/
          ├─ common
          ├─ css
          ├─ guest
          ├─ images
          ├─ manager
          ├─ user
          ├─ META-INF
          └─ WEB-INF/
```
## 🧱 データベース設計（Database Schema）
```
テーブル：
users：利用者情報
managers：管理者情報
properties：物件情報
reviews：口コミ

```
## 🚀 セットアップ方法（How to Run）
```
1. リポジトリを clone
git clone https://github.com/your-name/rental_room.git

2. MySQL にデータベース作成
CREATE DATABASE rental_room CHARACTER SET utf8mb4;

3. 初期データを import
mysql -u root -p rental_room < dump.sql

4. Tomcat で起動

Eclipse から
Run → Run on Server を選択。
```
### 📸 スクリーンショット（Screenshots）
🎯 このアプリのポイント（Highlights）
```
MVC + FrontController による拡張性ある構造

DAOパターン を用いたDBアクセス

PreparedStatement によるSQLインジェクション対策

JSTL <c:out> によるXSS対策

画像名を utf8mb4_bin で厳密に管理

シンプル＆見やすいUI
```
## 📝 今後の改善予定（Future Work）
```
物件カテゴリ機能

レビュー検索機能

人気物件ランキング

Render等での本番サーバー公開

Google Maps API の導入
```
## 👤 作者（Author）
```
みつひこ（飯島）

Polytech Center Chiba

Java Web Developer

GitHub : https://github.com/your-name              

```
