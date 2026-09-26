-- 가상 멤버 10명. member 테이블이 생성된 뒤 IntelliJ DB 콘솔에서 실행한다.
INSERT INTO member (name, email, created_at, fee_required, role, status) VALUES
('가상회원01', 'member01@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원02', 'member02@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원03', 'member03@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원04', 'member04@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원05', 'member05@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원06', 'member06@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원07', 'member07@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원08', 'member08@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상회원09', 'member09@example.test', CURRENT_TIMESTAMP, true, 'MEMBER', 'ACTIVE'),
('가상운영자', 'organizer@example.test', CURRENT_TIMESTAMP, false, 'ORGANIZER', 'ACTIVE');
