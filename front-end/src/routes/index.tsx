import { createFileRoute } from '@tanstack/react-router'
import bannerMobile from '@/assets/banner_home_mobile.webp'
import banner from '@/assets/banner_home.webp'
import { useIsMobile } from '@/hooks/use-mobile'
export const Route = createFileRoute('/')({
  component: App,
})

function App() {
  const isMobile = useIsMobile()
  console.log(isMobile)
  return (
    <>
      <div id="banner-img" className="relative h-[300px]">
        <img
          src={isMobile ? bannerMobile : banner}
          alt="Discover top book clubs"
          className="w-full "
        />
        <div id="banner-content "> hello</div>
      </div>
      <p>Content</p>
    </>
  )
}
