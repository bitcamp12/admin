function showModal(message) {
    // 메시지를 동적으로 설정
    document.getElementById('modalMessage').textContent = message;

    // 부트스트랩 모달 띄우기
    const Modal = new bootstrap.Modal(document.getElementById('Modal'));
    Modal.show();
}