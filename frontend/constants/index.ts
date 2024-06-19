// hardcode for demo
export type DisplayAccount = {
  username: string,
  name: string,
  profilePic: string,
  verified: boolean,
}

export const displayAccounts : DisplayAccount[] = [
  {
    username: "tiktok",
    name: "TikTok",
    profilePic: "/images/profilepics/tiktok.jpeg",
    verified: true
  },
  {
    username: "shou.time",
    name: "Shou",
    profilePic: "/images/profilepics/shou.jpeg",
    verified: true
  },
]

export const sidebarFooterCompany : string[] = [
  "About",
  "Newsroom",
  "Contact",
  "Careers"
]

export const sidebarFooterPrograms : string[] = [
  "TikTok for Good",
  "Advertise",
  "TikTok LIVE Creator Networks",
  "Developers",
  "Transparency",
  "TikTok Rewards",
  "TikTok Embeds",
  "TikTok Music",
]

export const sidebarFooterTermsPolicies : string[] = [
  "Help",
  "Safety",
  "Terms",
  "Privacy Policy",
  "Privacy Center",
  "Creator Academy",
  "Community Guidelines"
]