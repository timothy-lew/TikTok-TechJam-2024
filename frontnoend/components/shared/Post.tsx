import Image from "next/image"

type PostProps = {
    image: string,
    name: string,
    verified: boolean
    desc: string,
    profile: string,
    likes: number,
    comments: number,
    bookmarks: number,
    shares: number,
}

const Post = ({image, name, verified, desc, profile, likes, comments, bookmarks, shares} : PostProps) => {
    return (
        <div className="flex w-3/5 max-w-[800px] max-h-screen">
            <div className="flex-grow relative h-screen py-[100px]">
                <div className="w-full h-full relative">
                    <Image
                        className="object-cover rounded-xl"
                        src={image}
                        alt="post"
                        fill={true}
                        priority
                    />
                    <div className="flex flex-col p-4 justify-center items-start gap-1 absolute bottom-1">
                        <div className="flex_center gap-2">
                            <p className="font-bold text-white">{name}</p>
                            {verified && <svg fontSize="14px" viewBox="0 0 48 48" fill="currentColor" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em"><g clipPath="url(#Icon_Color-Verified_Badge_svg__a)"><path d="M0 24a24 24 0 1 1 48 0 24 24 0 0 1-48 0Z" fill="#20D5EC"></path><path fillRule="evenodd" clipRule="evenodd" d="M37.12 15.88a3 3 0 0 1 0 4.24l-13.5 13.5a3 3 0 0 1-4.24 0l-8.5-8.5a3 3 0 1 1 4.24-4.24l6.38 6.38 11.38-11.38a3 3 0 0 1 4.24 0Z" fill="#fff"></path></g><defs><clipPath id="Icon_Color-Verified_Badge_svg__a"><path fill="#fff" d="M0 0h48v48H0z"></path></clipPath></defs></svg>}
                        </div>
                        <p className="text-white">{desc}</p>
                    </div>
                </div>
            </div>

            <div className="flex flex-col items-center gap-6 justify-end w-[80px] h-screen py-[110px]">
                <div className="flex_center relative">
                    <Image src={profile} alt="dp" height={50} width={50} className="rounded-full" />
                    <div className="text-white text-xs bg-red-600 rounded-full py-0.5 px-1.5 absolute -bottom-2.5">+</div>
                </div>

                <div className="flex_col_center gap-1">
                    <div className="bg-gray-200 rounded-full flex_center h-[45px] w-[45px]">
                        <svg width="24" data-e2e="" height="24" viewBox="0 0 24 24" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><g clipPath="url(#HeartFill_clip0)"><g filter="url(#HeartFill_filter0_d)"><path fillRule="evenodd" clipRule="evenodd" d="M7.5 2.25C10.5 2.25 12 4.25 12 4.25C12 4.25 13.5 2.25 16.5 2.25C20 2.25 22.5 4.99999 22.5 8.5C22.5 12.5 19.2311 16.0657 16.25 18.75C14.4095 20.4072 13 21.5 12 21.5C11 21.5 9.55051 20.3989 7.75 18.75C4.81949 16.0662 1.5 12.5 1.5 8.5C1.5 4.99999 4 2.25 7.5 2.25Z"></path></g><path fillRule="evenodd" clipRule="evenodd" d="M2.40179 12.1998C3.58902 14.6966 5.7592 16.9269 7.74989 18.75C9.5504 20.3989 10.9999 21.5 11.9999 21.5C12.9999 21.5 14.4094 20.4072 16.2499 18.75C19.231 16.0657 22.4999 12.5 22.4999 8.49997C22.4999 8.41258 22.4983 8.32566 22.4952 8.23923C20.5671 13.6619 13.6787 18.5 11.75 18.5C10.3127 18.5 5.61087 15.8131 2.40179 12.1998Z" fillOpacity="0.03"></path></g><defs><filter id="HeartFill_filter0_d" x="-0.9" y="1.05" width="25.8" height="24.05" filterUnits="userSpaceOnUse" colorInterpolationFilters="sRGB"><feFlood floodOpacity="0" result="BackgroundImageFix"></feFlood><feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix><feOffset dy="1.2"></feOffset><feGaussianBlur stdDeviation="1.2"></feGaussianBlur><feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.15 0"></feColorMatrix><feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow"></feBlend><feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow" result="shape"></feBlend></filter><clipPath id="HeartFill_clip0"><rect width="24" height="24" fill="white"></rect></clipPath></defs></svg>
                    </div>
                    <span className="font-semibold text-sm">
                        {likes}K
                    </span>
                </div>

                <div className="flex_col_center gap-1">
                    <div className="bg-gray-200 rounded-full flex_center h-[45px] w-[45px]">
                        <Image src="/icons/comment.svg" alt="comment" height={22} width={22}/>
                    </div>
                    <span className="font-semibold text-sm">
                        {comments}
                    </span>
                </div>

                <div className="flex_col_center gap-1">
                    <div className="bg-gray-200 rounded-full flex_center h-[45px] w-[45px]">
                        <Image src="/icons/bookmark.svg" alt="comment" height={20} width={20}/>
                    </div>
                    <span className="font-semibold text-sm">
                        {bookmarks}
                    </span>
                </div>

                <div className="flex_col_center gap-1">
                    <div className="bg-gray-200 rounded-full flex_center h-[45px] w-[45px]">
                        <Image src="/icons/share.png" alt="comment" height={20} width={20}/>
                    </div>
                    <span className="font-semibold text-sm">
                        {shares}
                    </span>
                </div>

                <div className="bg-gray-200 rounded-full flex_center h-[45px] w-[45px]">
                    <svg fill="currentColor" width="1em" height="1em" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"><path d="M4 24a5 5 0 1 1 10 0 5 5 0 0 1-10 0ZM19 24a5 5 0 1 1 10 0 5 5 0 0 1-10 0ZM39 19a5 5 0 1 0 0 10 5 5 0 0 0 0-10Z"></path></svg>
                </div>
            </div>
        </div>
    )
}

export default Post