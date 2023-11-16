function setRotation(element, rotationRatio) {
    element.style.setProperty('--rotation', rotationRatio)
  }
for (let i = 1 ; i <= 60 ; i++ ) {
    const marker = document.querySelector(`.markers${i}`);
    if (i % 5 === 0) {
        marker.children[0].style.width='5px'
        marker.children[0].innerHTML = `${i/5}`
       
    }
    setRotation(marker, i*6)
}

setInterval(rotateHands,1000)
const secHand = document.querySelector(".sec")
const minHand = document.querySelector(".min")
const hourHand = document.querySelector(".hour")

function rotateHands() {
    const currentTime = new Date()
    const second = currentTime.getSeconds() / 60
    const min = (second +currentTime.getMinutes()) / 60
    const hour = (min + currentTime.getHours()) / 12

    setRotation(secHand, second*360)
    setRotation(minHand, min*360)
    setRotation(hourHand, hour*360)


}

