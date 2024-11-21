//타임리프 body 불러오기
function loadClickEvent(elementId, url) {
	document.getElementById(elementId).addEventListener('click', function() {
		const mainContent = document.getElementById('mainContent');

		// 캐싱 방지 쿼리 추가
		const cacheBuster = `?t=${new Date().getTime()}`;
		const fetchUrl = `/secure/admin/${elementId}${cacheBuster}`;

		fetch(fetchUrl) // Fetch the content from the server with cache buster
			.then(response => response.text())
			.then(html => {
				mainContent.innerHTML = html; // Update the main content

				// 동적으로 추가된 <script> 태그 실행
				const scripts = mainContent.querySelectorAll('script');
				scripts.forEach(script => {
					const newScript = document.createElement('script');
					if (script.src) {
						// 외부 스크립트의 경우 src를 복사하여 새로 추가
						newScript.src = script.src;
						newScript.async = true; // 비동기 로드
					} else {
						// 인라인 스크립트의 경우 내용을 복사하여 실행
						newScript.textContent = script.innerHTML;
					}
					document.body.appendChild(newScript); // 스크립트 실행
					script.remove(); // 중복 실행 방지
				});
			})
			.catch(err => console.error('Error loading content:', err));
	});
}

function loadBodyForm(elementId) {
	const mainContent = document.getElementById('mainContent');

	// 캐싱 방지 쿼리 추가
	const cacheBuster = `?t=${new Date().getTime()}`;
	const fetchUrl = `/secure/admin/${elementId}${cacheBuster}`;

	fetch(fetchUrl) // Fetch the content from the server with cache buster
		.then(response => response.text())
		.then(html => {
			mainContent.innerHTML = html; // Update the main content

			// 동적으로 추가된 <script> 태그 실행
			const scripts = mainContent.querySelectorAll('script');
			scripts.forEach(script => {
				const newScript = document.createElement('script');
				if (script.src) {
					// 외부 스크립트의 경우 src를 복사하여 새로 추가
					newScript.src = script.src;
					newScript.async = true; // 비동기 로드
				} else {
					// 인라인 스크립트의 경우 내용을 복사하여 실행
					newScript.textContent = script.innerHTML;
				}
				document.body.appendChild(newScript); // 스크립트 실행
				script.remove(); // 중복 실행 방지
			});
		})
		.catch(err => console.error('Error loading content:', err));
}