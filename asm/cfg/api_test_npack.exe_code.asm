0x00404290:	cmpl 0x404e4c, $0x0<UINT8>
0x00404297:	jne 5
0x00404299:	jmp 0x0040429f
0x0040429f:	call 0x004042ea
0x004042ea:	pushl %esi
0x004042eb:	pushl %edi
0x004042ec:	pushl $0x404180<UINT32>
0x004042f1:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x004042f7:	movl %esi, 0x404c08
0x004042fd:	movl %edi, %eax
0x004042ff:	pushl $0x404190<UINT32>
0x00404304:	pushl %edi
0x00404305:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00404307:	pushl $0x4041a0<UINT32>
0x0040430c:	pushl %edi
0x0040430d:	movl 0x404e48, %eax
0x00404312:	call GetProcAddress@kernel32.dll
0x00404314:	popl %edi
0x00404315:	movl 0x404e44, %eax
0x0040431a:	popl %esi
0x0040431b:	ret

0x004042a4:	call 0x0040431c
0x0040431c:	pushl %esi
0x0040431d:	pushl %edi
0x0040431e:	pushl $0x4<UINT8>
0x00404320:	pushl $0x3000<UINT32>
0x00404325:	pushl $0x24<UINT8>
0x00404327:	pushl $0x0<UINT8>
0x00404329:	call VirtualAlloc@KERNEL32.DLL
VirtualAlloc@KERNEL32.DLL: API Node	
0x0040432f:	movl %esi, %eax
0x00404331:	movl %edi, $0x404e00<UINT32>
0x00404336:	pushl %esi
0x00404337:	pushl %edi
0x00404338:	call 0x00404160
0x00404160:	movl %eax, 0x8(%esp)
0x00404164:	movl %ecx, 0x4(%esp)
0x00404168:	pushl %eax
0x00404169:	pushl %ecx
0x0040416a:	call 0x00404b30
0x00404b30:	pusha
0x00404b31:	movl %esi, 0x24(%esp)
0x00404b35:	movl %edi, 0x28(%esp)
0x00404b39:	cld
0x00404b3a:	movb %dl, $0xffffff80<UINT8>
0x00404b3c:	xorl %ebx, %ebx
0x00404b3e:	movsb %es:(%edi), %ds:(%esi)
0x00404b3f:	movb %bl, $0x2<UINT8>
0x00404b41:	call 0x00404bb3
0x00404bb3:	addb %dl, %dl
0x00404bb5:	jne 0x00404bbc
0x00404bb7:	movb %dl, (%esi)
0x00404bb9:	incl %esi
0x00404bba:	adcb %dl, %dl
0x00404bbc:	ret

0x00404b46:	jae 0x00404b3e
0x00404b48:	xorl %ecx, %ecx
0x00404b4a:	call 0x00404bb3
0x00404b4f:	jae 0x00404b6d
0x00404b51:	xorl %eax, %eax
0x00404b53:	call 0x00404bb3
0x00404b58:	jae 0x00404b7d
0x00404b5a:	movb %bl, $0x2<UINT8>
0x00404b5c:	incl %ecx
0x00404b5d:	movb %al, $0x10<UINT8>
0x00404b5f:	call 0x00404bb3
0x00404b64:	adcb %al, %al
0x00404b66:	jae 0x00404b5f
0x00404b68:	jne 0x00404ba9
0x00404b6a:	stosb %es:(%edi), %al
0x00404b6b:	jmp 0x00404b41
0x00404b7d:	lodsb %al, %ds:(%esi)
0x00404b7e:	shrl %eax
0x00404b80:	je 0x00404bcf
0x00404b82:	adcl %ecx, %ecx
0x00404b84:	jmp 0x00404ba2
0x00404ba2:	incl %ecx
0x00404ba3:	incl %ecx
0x00404ba4:	xchgl %ebp, %eax
0x00404ba5:	movl %eax, %ebp
0x00404ba7:	movb %bl, $0x1<UINT8>
0x00404ba9:	pushl %esi
0x00404baa:	movl %esi, %edi
0x00404bac:	subl %esi, %eax
0x00404bae:	rep movsb %es:(%edi), %ds:(%esi)
0x00404bb0:	popl %esi
0x00404bb1:	jmp 0x00404b41
0x00404b6d:	call 0x00404bbf
0x00404bbf:	incl %ecx
0x00404bc0:	call 0x00404bb3
0x00404bc5:	adcl %ecx, %ecx
0x00404bc7:	call 0x00404bb3
0x00404bcc:	jb 0x00404bc0
0x00404bce:	ret

0x00404b72:	subl %ecx, %ebx
0x00404b74:	jne 0x00404b86
0x00404b76:	call 0x00404bbd
0x00404bbd:	xorl %ecx, %ecx
0x00404b7b:	jmp 0x00404ba5
0x00404b86:	xchgl %ecx, %eax
0x00404b87:	decl %eax
0x00404b88:	shll %eax, $0x8<UINT8>
0x00404b8b:	lodsb %al, %ds:(%esi)
0x00404b8c:	call 0x00404bbd
0x00404b91:	cmpl %eax, $0x7d00<UINT32>
0x00404b96:	jae 10
0x00404b98:	cmpb %ah, $0x5<UINT8>
0x00404b9b:	jae 6
0x00404b9d:	cmpl %eax, $0x7f<UINT8>
0x00404ba0:	ja 0x00404ba4
0x00404bcf:	subl %edi, 0x28(%esp)
0x00404bd3:	movl 0x1c(%esp), %edi
0x00404bd7:	popa
0x00404bd8:	ret

0x0040416f:	addl %esp, $0x8<UINT8>
0x00404172:	ret

0x0040433d:	pushl $0x24<UINT8>
0x0040433f:	pushl %esi
0x00404340:	pushl %edi
0x00404341:	call 0x0040403a
0x0040403a:	pushl %ebp
0x0040403b:	movl %ebp, %esp
0x0040403d:	pushl %esi
0x0040403e:	pushl %edi
0x0040403f:	movl %ecx, 0x10(%ebp)
0x00404042:	movl %esi, 0xc(%ebp)
0x00404045:	movl %edi, 0x8(%ebp)
0x00404048:	rep movsb %es:(%edi), %ds:(%esi)
0x0040404a:	popl %edi
0x0040404b:	popl %esi
0x0040404c:	popl %ebp
0x0040404d:	ret

0x00404346:	addl %esp, $0x14<UINT8>
0x00404349:	pushl $0x4000<UINT32>
0x0040434e:	pushl $0x0<UINT8>
0x00404350:	pushl %esi
0x00404351:	call VirtualFree@KERNEL32.DLL
VirtualFree@KERNEL32.DLL: API Node	
0x00404357:	popl %edi
0x00404358:	popl %esi
0x00404359:	ret

0x004042a9:	movl %eax, $0x404290<UINT32>
0x004042ae:	subl %eax, 0x404e08
0x004042b4:	movl 0x404e40, %eax
0x004042b9:	call 0x0040435a
0x0040435a:	subl %esp, $0x10<UINT8>
0x0040435d:	pushl %ebp
0x0040435e:	pushl %esi
0x0040435f:	pushl %edi
0x00404360:	xorl %ebp, %ebp
0x00404362:	pushl 0x404e40
0x00404368:	movl 0x18(%esp), %ebp
0x0040436c:	call 0x00404030
0x00404030:	movl %ecx, 0x4(%esp)
0x00404034:	movl %eax, 0x3c(%ecx)
0x00404037:	addl %eax, %ecx
0x00404039:	ret

0x00404371:	movl %edi, %eax
0x00404373:	popl %ecx
0x00404374:	movl 0x18(%esp), %edi
0x00404378:	movl 0x10(%esp), %ebp
0x0040437c:	movzwl %eax, 0x14(%edi)
0x00404380:	leal %esi, 0x18(%eax,%edi)
0x00404384:	movzwl %eax, 0x6(%edi)
0x00404388:	decl %eax
0x00404389:	testl %eax, %eax
0x0040438b:	jle 371
0x00404391:	pushl %ebx
0x00404392:	cmpl 0x10(%esi), %ebp
0x00404395:	movl 0x10(%esp), %ebp
0x00404399:	je 334
0x0040439f:	cmpl 0x14(%esi), %ebp
0x004043a2:	je 325
0x004043a8:	testb 0x27(%esi), $0x10<UINT8>
0x004043ac:	je 0x004043bb
0x004043bb:	pushl %edi
0x004043bc:	pushl %esi
0x004043bd:	pushl $0x2<UINT8>
0x004043bf:	call 0x00404000
0x00404000:	movl %eax, 0x8(%esp)
0x00404004:	movl %edx, 0x8(%eax)
0x00404007:	testl %edx, %edx
0x00404009:	jne 0x0040400e
0x0040400e:	movl %ecx, 0xc(%esp)
0x00404012:	movl %eax, 0xc(%eax)
0x00404015:	pushl %esi
0x00404016:	movl %esi, 0x8(%esp)
0x0040401a:	movl %ecx, 0x78(%ecx,%esi,8)
0x0040401e:	popl %esi
0x0040401f:	cmpl %ecx, %eax
0x00404021:	jb 0x0040402d
0x0040402d:	xorl %eax, %eax
0x0040402f:	ret

0x004043c4:	addl %esp, $0xc<UINT8>
0x004043c7:	testl %eax, %eax
0x004043c9:	je 0x004043df
0x004043df:	pushl %edi
0x004043e0:	pushl %esi
0x004043e1:	pushl %ebp
0x004043e2:	call 0x00404000
0x004043e7:	addl %esp, $0xc<UINT8>
0x004043ea:	testl %eax, %eax
0x004043ec:	jne 251
0x004043f2:	movl %eax, 0x10(%esi)
0x004043f5:	movl %edi, $0x3000<UINT32>
0x004043fa:	pushl $0x4<UINT8>
0x004043fc:	pushl %edi
0x004043fd:	pushl %eax
0x004043fe:	pushl %ebp
0x004043ff:	call VirtualAlloc@KERNEL32.DLL
0x00404405:	cmpl 0x10(%esp), %ebp
0x00404409:	movl %ebx, %eax
0x0040440b:	je 0x00404458
0x00404458:	movl %eax, 0xc(%esi)
0x0040445b:	pushl 0x10(%esi)
0x0040445e:	addl %eax, 0x404e40
0x00404464:	pushl %eax
0x00404465:	pushl %ebx
0x00404466:	call 0x0040403a
0x0040446b:	addl %esp, $0xc<UINT8>
0x0040446e:	movl %edi, 0xc(%esi)
0x00404471:	addl %edi, 0x404e40
0x00404477:	pushl %edi
0x00404478:	pushl %ebx
0x00404479:	call 0x00404160
0x0040447e:	movl %ebp, %eax
0x00404480:	popl %ecx
0x00404481:	cmpl %ebp, $0xffffffff<UINT8>
0x00404484:	popl %ecx
0x00404485:	je 16
0x00404487:	movb %al, 0x404e18
0x0040448c:	pushl %eax
0x0040448d:	pushl %ebp
0x0040448e:	pushl %edi
0x0040448f:	call 0x0040405f
0x0040405f:	movl %ecx, 0x8(%esp)
0x00404063:	movl %eax, 0x4(%esp)
0x00404067:	movb %dl, 0xc(%esp)
0x0040406b:	addl %eax, %ecx
0x0040406d:	xorb (%eax), %dl
0x0040406f:	decl %ecx
0x00404070:	jne 0x00404063
0x00404072:	ret

0x00404494:	addl %esp, $0xc<UINT8>
0x00404497:	cmpl 0x10(%esp), $0x0<UINT8>
0x0040449c:	jne 15
0x0040449e:	pushl $0x1<UINT8>
0x004044a0:	pushl 0xc(%esi)
0x004044a3:	pushl %ebp
0x004044a4:	pushl %edi
0x004044a5:	call 0x00404073
0x00404073:	pushl %ebp
0x00404074:	movl %ebp, %esp
0x00404076:	subl %esp, $0xc<UINT8>
0x00404079:	pushl %esi
0x0040407a:	pushl $0x5<UINT8>
0x0040407c:	popl %eax
0x0040407d:	xorl %esi, %esi
0x0040407f:	cmpl 0xc(%ebp), %eax
0x00404082:	movl -12(%ebp), $0xfffffffc<UINT32>
0x00404089:	jbe 200
0x0040408f:	movl -8(%ebp), %eax
0x00404092:	movl %eax, 0x10(%ebp)
0x00404095:	incl %eax
0x00404096:	pushl %ebx
0x00404097:	movl %ebx, 0x8(%ebp)
0x0040409a:	pushl %edi
0x0040409b:	movl -4(%ebp), %eax
0x0040409e:	movb %al, (%esi,%ebx)
0x004040a1:	cmpb %al, $0xffffffe8<UINT8>
0x004040a3:	je 0x004040bd
0x004040a5:	cmpb %al, $0xffffffe9<UINT8>
0x004040a7:	je 20
0x004040a9:	movw %cx, (%esi,%ebx)
0x004040ad:	andw %cx, $0xfffff0ff<UINT16>
0x004040b2:	cmpw %cx, $0xffff800f<UINT16>
0x004040b7:	jne 0x00404142
0x00404142:	incl %esi
0x00404143:	incl -4(%ebp)
0x00404146:	incl -8(%ebp)
0x00404149:	movl %eax, -8(%ebp)
0x0040414c:	cmpl %eax, 0xc(%ebp)
0x0040414f:	jb 0x0040409e
0x004040bd:	cmpb %al, $0xf<UINT8>
0x004040bf:	jne 0x004040c8
0x004040c8:	movb %al, 0x4(%esi,%ebx)
0x004040cc:	movl %edx, %esi
0x004040ce:	subl %edx, -12(%ebp)
0x004040d1:	movl -12(%ebp), %esi
0x004040d4:	xorl %edx, $0x3<UINT8>
0x004040d7:	cmpb %al, $0xffffffff<UINT8>
0x004040d9:	je 4
0x004040db:	testb %al, %al
0x004040dd:	jne 99
0x004040df:	movl %eax, 0x1(%esi,%ebx)
0x004040e3:	cmpl 0x14(%ebp), $0x0<UINT8>
0x004040e7:	je 10
0x004040e9:	subl %eax, %esi
0x004040eb:	subl %eax, 0x10(%ebp)
0x004040ee:	decl %eax
0x004040ef:	movl %edi, %eax
0x004040f1:	jmp 0x004040f9
0x004040f9:	cmpl %edx, $0x3<UINT8>
0x004040fc:	movl 0x8(%ebp), %edi
0x004040ff:	ja 0x0040411d
0x0040411d:	testl %edi, $0x1000000<UINT32>
0x00404123:	je 0x0040412d
0x0040412d:	andl %edi, $0xffffff<UINT32>
0x00404133:	addl -4(%ebp), $0x4<UINT8>
0x00404137:	movl 0x1(%esi,%ebx), %edi
0x0040413b:	addl %esi, $0x4<UINT8>
0x0040413e:	addl -8(%ebp), $0x4<UINT8>
0x00404155:	popl %edi
0x00404156:	popl %ebx
0x00404157:	popl %esi
0x00404158:	leave
0x00404159:	ret

0x004044aa:	addl %esp, $0x10<UINT8>
0x004044ad:	movl %ebp, $0x4000<UINT32>
0x004044b2:	pushl %ebp
0x004044b3:	pushl $0x0<UINT8>
0x004044b5:	pushl %ebx
0x004044b6:	call VirtualFree@KERNEL32.DLL
0x004044bc:	cmpl 0x10(%esp), $0x0<UINT8>
0x004044c1:	je 0x004044e2
0x004044e2:	movl %edi, 0x1c(%esp)
0x004044e6:	addl %esi, $0x28<UINT8>
0x004044e9:	xorl %ebp, %ebp
0x004044eb:	jmp 0x004044f0
0x004044f0:	movzwl %eax, 0x6(%edi)
0x004044f4:	incl 0x14(%esp)
0x004044f8:	decl %eax
0x004044f9:	cmpl 0x14(%esp), %eax
0x004044fd:	jl 0x00404392
0x00404503:	popl %ebx
0x00404504:	popl %edi
0x00404505:	popl %esi
0x00404506:	popl %ebp
0x00404507:	addl %esp, $0x10<UINT8>
0x0040450a:	ret

0x004042be:	call 0x0040450b
0x0040450b:	movl %eax, 0x404e04
0x00404510:	subl %esp, $0xc<UINT8>
0x00404513:	pushl %esi
0x00404514:	xorl %esi, %esi
0x00404516:	cmpl %eax, %esi
0x00404518:	pushl %edi
0x00404519:	je 65
0x0040451b:	movl %ecx, 0x404e40
0x00404521:	cmpl 0x10(%ecx,%eax), %esi
0x00404525:	leal %edi, (%ecx,%eax)
0x00404528:	je 50
0x0040452a:	pushl %ebx
0x0040452b:	pushl %ebp
0x0040452c:	movl %ebx, 0xc(%edi)
0x0040452f:	addl %ebx, 0x404e40
0x00404535:	pushl %ebx
0x00404536:	call LoadLibraryA@kernel32.dll
0x0040453c:	cmpl %eax, %esi
0x0040453e:	movl 0x14(%esp), %eax
0x00404542:	jne 0x00404562
0x00404562:	movl %ebp, (%edi)
0x00404564:	cmpl %ebp, %esi
0x00404566:	jne 0x0040456b
0x0040456b:	movl %eax, 0x404e40
0x00404570:	movl %esi, 0x10(%edi)
0x00404573:	addl %ebp, %eax
0x00404575:	addl %esi, %eax
0x00404577:	movl %eax, (%ebp)
0x0040457a:	testl %eax, %eax
0x0040457c:	je 0x00404550
0x0040457e:	testl %eax, $0x80000000<UINT32>
0x00404583:	je 0x004045ae
0x004045ae:	movl %ecx, 0x404e40
0x004045b4:	addl %eax, %ecx
0x004045b6:	addl %eax, $0x2<UINT8>
0x004045b9:	pushl %eax
0x004045ba:	movl 0x1c(%esp), %eax
0x004045be:	pushl 0x18(%esp)
0x004045c2:	call GetProcAddress@kernel32.dll
0x004045c8:	testl %eax, %eax
0x004045ca:	movl 0x10(%esp), %eax
0x004045ce:	jne 0x004045e0
0x004045e0:	movl %eax, 0x10(%esp)
0x004045e4:	addl %ebp, $0x4<UINT8>
0x004045e7:	movl (%esi), %eax
0x004045e9:	addl %esi, $0x4<UINT8>
0x004045ec:	jmp 0x00404577
0x00404550:	addl %edi, $0x14<UINT8>
0x00404553:	xorl %esi, %esi
0x00404555:	cmpl 0x10(%edi), %esi
0x00404558:	jne 0x0040452c
0x0040455a:	popl %ebp
0x0040455b:	popl %ebx
0x0040455c:	popl %edi
0x0040455d:	popl %esi
0x0040455e:	addl %esp, $0xc<UINT8>
0x00404561:	ret

0x004042c3:	call 0x004049c0
0x004049c0:	pushl %ecx
0x004049c1:	pushl %ebx
0x004049c2:	pushl %ebp
0x004049c3:	pushl %esi
0x004049c4:	pushl %edi
0x004049c5:	pushl 0x404e40
0x004049cb:	call 0x00404030
0x004049d0:	movl %edx, 0x404e40
0x004049d6:	movl %edi, 0x404e14
0x004049dc:	movl %esi, %edx
0x004049de:	popl %ecx
0x004049df:	subl %esi, 0x34(%eax)
0x004049e2:	leal %ecx, (%edi,%edx)
0x004049e5:	je 0x00404a95
0x00404a95:	popl %edi
0x00404a96:	popl %esi
0x00404a97:	popl %ebp
0x00404a98:	popl %ebx
0x00404a99:	popl %ecx
0x00404a9a:	ret

0x004042c8:	call 0x00404914
0x00404914:	pushl %ebp
0x00404915:	movl %ebp, %esp
0x00404917:	pushl %ecx
0x00404918:	pushl 0x404e40
0x0040491e:	call 0x00404030
0x00404923:	cmpl 0xc0(%eax), $0x0<UINT8>
0x0040492a:	popl %ecx
0x0040492b:	je 0x00404975
0x00404975:	cmpl 0x404e58, $0x0<UINT8>
0x0040497c:	pushl %ebx
0x0040497d:	pushl %esi
0x0040497e:	pushl %edi
0x0040497f:	movl 0x404e54, $0x1<UINT32>
0x00404989:	je 0x004049bb
0x004049bb:	popl %edi
0x004049bc:	popl %esi
0x004049bd:	popl %ebx
0x004049be:	leave
0x004049bf:	ret

0x004042cd:	movl %eax, 0x404e40
0x004042d2:	movl 0x404e4c, $0x1<UINT32>
0x004042dc:	addl 0x404e00, %eax
0x004042e2:	pushl 0x404e00
0x004042e8:	ret

0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@kernel32.dll
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@kernel32.dll
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
