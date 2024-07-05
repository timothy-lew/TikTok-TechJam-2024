import Link from "next/link"

const AuthOption = ({ title, iconSVG, hrefLink }: { title: string, iconSVG: JSX.Element, hrefLink: string, }) => {
    return (
      <Link
		    href={hrefLink}
      	className="flex justify-center items-center w-[300px] px-3 py-2.5 border border-slate-200 rounded-md bg-gray-50 hover:bg-gray-100 cursor-pointer"
        >
        
        {iconSVG}
        <p className="font-semibold text-sm flex-grow flex_center">{title}</p>
      </Link>
    )
}

export default AuthOption;