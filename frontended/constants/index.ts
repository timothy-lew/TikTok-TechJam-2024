// hardcode for demo
export type DisplayAccount = {
  userId: string,
  username: string,
  name: string,
  profilePic: string,
  verified: boolean,
}

export const displayAccounts : DisplayAccount[] = [
  {
    userId: "Test",
    username: "tiktok",
    name: "TikTok",
    profilePic: "/images/profilepics/tiktok.jpeg",
    verified: true
  },
  {
    userId: "Test",
    username: "shou.time",
    name: "Shou",
    profilePic: "/images/profilepics/shou.jpeg",
    verified: true
  },
  {
    userId: "Test",
    username: "alxxman",
    name: "Alex",
    profilePic: "/images/profilepics/alxxman.jpeg",
    verified: false
  },
  {
    userId: "Test",
    username: "ji4e4rn",
    name: "Jiaearn",
    profilePic: "/images/profilepics/ji4e4rn.jpeg",
    verified: false
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