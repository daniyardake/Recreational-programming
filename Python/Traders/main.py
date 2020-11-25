import pandas as pd
import matplotlib.pyplot as plt

def to_hrs(milsecs):
    return (milsecs/(3600000))

users_df = pd.read_csv('users.tsv', sep='\t')
discussions_df = pd.read_csv('discussions.tsv', sep='\t')
messages_df = pd.read_csv('messages.tsv', sep='\t')
disposts_df = pd.read_csv('discussion_posts.tsv', sep='\t')


#<----------------------Question 1.1----------------------->
# How many users are in the database? Deliverable: A number. 
print(f'There are {len(users_df)} Traders')
#------------------------------------------------------------

#<----------------------Question 1.2----------------------->
# What is the time span of the database? Deliverable: The difference between the largest
# and the smallest timestamps in the database, a number. 

time_span = pd.concat([
        users_df['memberSince'] ,
        discussions_df['createDate'] ,
        messages_df['sendDate'] ,
        disposts_df['createDate'] 
            ],ignore_index=True)
print(f'The difference between the largest and the smallest timestamps is: {to_hrs(time_span.max()-time_span.min())} hrs')
#------------------------------------------------------------

#<----------------------Question 1.3----------------------->
# How many messages of each type have been sent? Deliverable: A pie chart. 
plt.figure(figsize=(12,12))
plt.pie([len(messages_df.loc[messages_df['type']=='FRIEND_LINK_REQUEST']), len(messages_df.loc[messages_df['type']=='DIRECT_MESSAGE'])], labels=['Friend Link Requests', 'Direct Messages'], colors = ['gold', 'red'], shadow = True, startangle=19, autopct=lambda p: '{:.0f}'.format(p * (len(messages_df.loc[messages_df['type']=='FRIEND_LINK_REQUEST']) +len(messages_df.loc[messages_df['type']=='DIRECT_MESSAGE'])) / 100))
plt.savefig('1.3.png')
plt.clf()
#------------------------------------------------------------

#<----------------------Question 1.4----------------------->
# How many discussions of each type have been started? Deliverable: A pie chart. 
plt.figure(figsize=(12,12))
a = discussions_df.loc[discussions_df['discussionCategory']=='ECONOMICEVENT']
b = discussions_df.loc[discussions_df['discussionCategory']=='FEED_ITEM']
c = discussions_df.loc[discussions_df['discussionCategory']=='MARKET_COMMENTARY']
d = discussions_df.loc[discussions_df['discussionCategory']=='NEWSREPORT']
e = discussions_df.loc[discussions_df['discussionCategory']=='POLL']
f = discussions_df.loc[discussions_df['discussionCategory']=='POSITION']
g = discussions_df.loc[discussions_df['discussionCategory']=='QUESTION']
h = discussions_df.loc[discussions_df['discussionCategory']=='TECHNICAL_ANALYSIS']
i = discussions_df.loc[discussions_df['discussionCategory']=='TECHNICAL_INDICATOR']
discategories = [len(a),len(b),len(c),len(d),len(e),len(f),len(g),len(h),len(i)]
plt.pie(discategories, labels=['ECONOMICEVENT', 'FEED_ITEM', 'MARKET_COMMENTARY', 'NEWSREPORT', 'POLL', 'POSITION', 'QUESTION', 'TECHNICAL_ANALYSIS', 'TECHNICAL_INDICATOR'], shadow = True,  autopct=lambda p: '{:.0f}'.format(   p * (   sum(discategories) )  / 100    )        )
plt.savefig('1.4.png')
plt.clf()
#------------------------------------------------------------

#<----------------------Question 1.5----------------------->
# How many discussion posts have been posted? Deliverable: A number.
print(f'There are {len(disposts_df)} discussion posts posted')
#------------------------------------------------------------

#<----------------------Question 2----------------------->
# Activity range is the time between the first and the last message (in ANY category) sent by the same user.
# What is the distribution of activity ranges? Deliverable: a histogram. 
plt.figure(figsize=(12,12))
activities_df = pd.merge(users_df, messages_df, left_on='id', right_on='sender_id').groupby(["id_x"])
activity = to_hrs(activities_df.sendDate.max() - activities_df.sendDate.min())

activity.plot.hist(logy=True)
plt.xlabel('Activity Range')
plt.ylabel('Traders')
plt.savefig("2.png")
plt.clf()
#------------------------------------------------------------

#<----------------------Question 3----------------------->
# Message activity delay is the time between user account creation and sending the
# first user message in a specific category. What is the distribution of message activity
# delays in EACH category? Deliverable: a histogram for each category (ideally all histograms
# shall be in the same chart, semi-transparent, with legend).

plt.figure(figsize=(12,12))
activity_delay_df = activities_df.min()

friend_request_df = activity_delay_df.loc[activity_delay_df['type'] == 'FRIEND_LINK_REQUEST']
messages_df = activity_delay_df.loc[activity_delay_df['type'] == 'DIRECT_MESSAGE']

friend_request_df = (friend_request_df.sendDate - friend_request_df.memberSince)/(60*60*24*1000)
messages_df = (messages_df.sendDate - messages_df.memberSince)/(60*60*24*1000)

friend_request_df.plot.hist(legend=True, stacked=True, label='Friend link request', alpha=0.5, logy=True)
messages_df.plot.hist(legend=True, stacked=True, label='Direct Message', logy=True)
plt.ylabel("Traders")
plt.xlabel("Days")
plt.savefig("3.png")
plt.clf()

#------------------------------------------------------------

#<----------------------Question 4----------------------->
# What is the distribution of discussion categories by the number of posts?
# What is the most popular category? Deliverable: a pie chart, with the most popular category highlighted.

plt.figure(figsize=(12,12))

discussion_dist_df = pd.merge(disposts_df, discussions_df, left_on='discussion_id', right_on='id')
discusstion_data = discussion_dist_df.discussionCategory.value_counts()
discusstion_data.plot.pie(legend=True, labeldistance=1.2, shadow=True, autopct='%1.1f%%', pctdistance=1.1, explode = (0.2, 0, 0,0,0,0.1,-0.2,0.3,0)).set_ylabel("")

plt.savefig("4.png")
plt.clf()
#------------------------------------------------------------

#<----------------------Question 5----------------------->
# Post activity delay is the time between user account creation and posting the first discussion message.
# What is the distribution of post activity delays in the most popular category?
# Deliverable: a histogram. Note: The most popular category shall be carried over from the previous question.
plt.figure(figsize=(12,12))

activity_df = pd.merge(disposts_df, users_df, left_on='creator_id', right_on='id')
post_activ_df = activity_df.groupby("creator_id").min()
post_activ_df = (post_activ_df.createDate - post_activ_df.memberSince)/(60*60*24*1000)


post_activ_df.plot.hist(logy=True, title="Post Activity Delay")
plt.xlabel('Days')
plt.ylabel("Traders")
plt.savefig("5.png")
plt.clf()
#------------------------------------------------------------

#<----------------------Question 6----------------------->
# A box plot with whiskers that shows all appropriate statistics for message
# activity delays in EACH category, post activity delays, and activity ranges.

plt.figure(figsize=(12,12))
plt.subplot(1, 4, 1)
plt.title("Friend Requests")
friend_request_df.plot.box(label="", showmeans=True).set_ylabel("Box plot")
plt.yscale('log')

plt.subplot(1, 4, 2)
plt.title("Direct message")
messages_df.plot.box(label="", showmeans=True)
plt.yscale('log')

plt.subplot(1, 4, 3)
plt.title("Post Activity")
post_activ_df.plot.box(label="", showmeans=True)
plt.yscale('log')

plt.subplot(1, 4, 4)
plt.title("Activity")
activity.plot.box(label="", showmeans=True)
plt.yscale('log')
plt.tight_layout()

plt.savefig("6.png")
plt.close()

#------------------------------------------------------------
plt.close()